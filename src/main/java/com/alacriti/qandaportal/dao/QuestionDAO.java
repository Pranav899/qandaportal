package com.alacriti.qandaportal.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.alacriti.qandaportal.delegate.GetConnection;
import com.alacriti.qandaportal.vo.Page;
import com.alacriti.qandaportal.vo.Question;
import com.alacriti.qandaportal.vo.QuestionPage;

public class QuestionDAO{
	
	static Logger log = Logger.getLogger(QuestionDAO.class);
	public static void increaseNumberOfViews(long questionId){
		Connection connection = GetConnection.requestConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		String query = "select question_no_of_views from QAP_question_tbl where question_id ="+questionId+";";
		long presentViews = 0;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			while(resultSet.next()){
				presentViews = resultSet.getLong(1)+1;
				statement.executeUpdate("update QAP_question_tbl set question_no_of_views = "+presentViews+" where question_id = "+questionId+";");
			}
		} catch (SQLException e){
			log.info(e);
		}finally{
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (SQLException e){
				log.info(e);
			}
		}
	}
	public static long getNumberOfAnswersAvailable(long questionId){
		Connection connection = GetConnection.requestConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		long numberOfAnswers = 0;
		try{
			statement = connection.createStatement();
			String query = "SELECT COUNT(answer_id) FROM QAP_answer_tbl WHERE question_id = "+questionId+";";
			resultSet = statement.executeQuery(query);
			while(resultSet.next()){
				numberOfAnswers = resultSet.getLong(1);
			}
		}catch (SQLException e) {
			log.info(e);
		}finally{
				try {
					resultSet.close();
					statement.close();
					connection.close();
				} catch (SQLException e) {
					log.info(e);
				}
		}
		return numberOfAnswers;
	}
	public static Question getQuestion(long questionId){
		Connection connection = GetConnection.requestConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		Question question = null;
		String query = "select * from QAP_question_tbl where question_id ="+questionId+";";
		try{
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			while(resultSet.next()){
				question = new Question(resultSet.getLong(1)
										,resultSet.getLong(2)
										,resultSet.getString(3)
										,resultSet.getString(4)
										,resultSet.getLong(5)
										,resultSet.getString(6)
										,resultSet.getString(7)
										,resultSet.getString(8)
										,getNumberOfAnswersAvailable(resultSet.getLong(1))
										,resultSet.getLong(9));
			}
		} catch (Exception e){
			log.info(e);
		} finally{
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (SQLException e){
				log.info(e);
			}
		}
		increaseNumberOfViews(questionId);
		return question;
	}
	
	private static int uniqueId = 1;
	public static QuestionPage getRecentlyAskedQuestions(){
		Connection connection = GetConnection.requestConnection();
		String query = "SELECT * FROM QAP_question_tbl ORDER BY question_doa desc;";
		Statement statement = null;
		ResultSet resultSet = null;
		Statement statementOut = null;
		ResultSet resultSetOut = null;
		List<Question> questionsOut = new ArrayList<Question>();
		QuestionPage  questionPage = new QuestionPage();
		int recordId = 1;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			int totalResults = 0;
			while(resultSet.next()){
				long numberOfAnswerAvailable = getNumberOfAnswersAvailable(resultSet.getLong(1));
				totalResults = totalResults+1;
				String pagination_query = "INSERT INTO QAP_question_pagination_tbl values (?,?,?,?,?,?,?,?,?,?,?,?);";
				PreparedStatement preparedStatement = connection.prepareStatement(pagination_query);
				preparedStatement.setLong(1, uniqueId);
				preparedStatement.setLong(2, recordId);
				preparedStatement.setLong(3,resultSet.getLong(1));
				preparedStatement.setLong(4,resultSet.getLong(2));
				preparedStatement.setString(5,resultSet.getString(3));
				preparedStatement.setString(6,resultSet.getString(4));
				preparedStatement.setLong(7,resultSet.getLong(5));
				preparedStatement.setString(8,resultSet.getString(6));
				preparedStatement.setString(9,resultSet.getString(7));
				preparedStatement.setString(10,resultSet.getString(8));
				preparedStatement.setLong(11, numberOfAnswerAvailable);
				preparedStatement.setLong(12, resultSet.getLong(10));
				preparedStatement.executeUpdate();
				recordId = recordId+1;
			}
			String queryOut = "select * from QAP_question_pagination_tbl where unique_id = 1 limit 0,5;";
			statementOut = connection.createStatement();
			resultSetOut = statementOut.executeQuery(queryOut);
			while(resultSetOut.next()){
				questionsOut.add(new Question(resultSetOut.getLong(3)
						,resultSetOut.getLong(4)
						,resultSetOut.getString(5)
						,resultSetOut.getString(6)
						,resultSetOut.getLong(7)
						,resultSetOut.getString(8)
						,resultSetOut.getString(9)
						,resultSetOut.getString(10)
						,getNumberOfAnswersAvailable(resultSetOut.getLong(3))
						,resultSetOut.getLong(11)));
			}
			int noOfPages = 0;
			if(totalResults/5 == 0){
				noOfPages =1;
			}
			else{
				if(totalResults%5 == 0){
					noOfPages = totalResults/5;
				}
				else{
					noOfPages = (totalResults/5)+1;
				}
			}
			List<Page> pages = new ArrayList<Page>();
			for(int i=0;i<noOfPages;i++){
				pages.add(new Page(uniqueId,i+1,i*5,noOfPages,1));
			}
			questionPage.setPages(pages);
			questionPage.setQuestions(questionsOut);
			} catch (Exception e){
				log.info(e);
			} finally{
				try {
					resultSetOut.close();
					statementOut.close();
					resultSet.close();
					statement.close();
					connection.close();
				} catch (SQLException e){
					log.info(e);
			}
		}
		uniqueId = uniqueId+1;
		return questionPage;
	}
	public static QuestionPage getRecentlyAskedQuestions(long uniqueId,long start,long noOfPages){
		List<Question> questionsOut = new ArrayList<Question>();
		List<Page> pages = new ArrayList<Page>();
		Connection connection = GetConnection.requestConnection();
		Statement statement = null;
		ResultSet resultSetOut = null;
		QuestionPage  questionPage = new QuestionPage();
		String query = "select * from QAP_question_pagination_tbl where unique_id = "+uniqueId+" and record_id > "+start+" limit 5;";
		log.info(query);
		try {
			statement = connection.createStatement();
			resultSetOut = statement.executeQuery(query);
			while(resultSetOut.next()){
				questionsOut.add(new Question(resultSetOut.getLong(3)
						,resultSetOut.getLong(4)
						,resultSetOut.getString(5)
						,resultSetOut.getString(6)
						,resultSetOut.getLong(7)
						,resultSetOut.getString(8)
						,resultSetOut.getString(9)
						,resultSetOut.getString(10)
						,getNumberOfAnswersAvailable(resultSetOut.getLong(3))
						,resultSetOut.getLong(11)));
			}
			long activePage = (start/5)+1;
			for(int i=0;i<noOfPages;i++){
				pages.add(new Page(uniqueId,i+1,i*5,noOfPages,activePage));
			}
			questionPage.setPages(pages);
			questionPage.setQuestions(questionsOut);
		} catch (SQLException e){
			log.info(e);
		}
		return questionPage;
	}
	public static List<Question> getMostViewedQuestions(){
		List<Question> questions = new ArrayList<Question>();
		String query = "SELECT * FROM QAP_question_tbl ORDER BY question_no_of_views desc limit 0,5;";
		Connection connection = GetConnection.requestConnection();
		Statement statement = null ;
		ResultSet resultSet = null;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			while(resultSet.next()){
				questions.add(new Question(resultSet.getLong(1)
										,resultSet.getLong(2)
										,resultSet.getString(3)
										,resultSet.getString(4)
										,resultSet.getLong(5)
										,resultSet.getString(6)
										,resultSet.getString(7)
										,resultSet.getString(8)
										,getNumberOfAnswersAvailable(resultSet.getLong(1))
										,resultSet.getLong(9)));
			}
			} catch (Exception e){
				log.info(e);
			} finally{
				try {
					resultSet.close();
					statement.close();
					connection.close();
				} catch (SQLException e) {
			}
		}
		return questions;
	}
	public static List<Question> getQuestionsByTopic(String topic){
		List<Question> questions = new ArrayList<Question>();
		String query = "SELECT * FROM QAP_question_tbl WHERE question LIKE '%"+topic+"%' OR question_key1 LIKE '%"+topic+"%' OR question_key2 LIKE '%"+topic+"%' OR question_key3 LIKE '%"+topic+"%';";
		Connection connection = GetConnection.requestConnection();
		Statement statement = null ;
		ResultSet resultSet = null;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			while(resultSet.next()){
				questions.add(new Question(resultSet.getLong(1)
										,resultSet.getLong(2)
										,resultSet.getString(3)
										,resultSet.getString(4)
										,resultSet.getLong(5)
										,resultSet.getString(6)
										,resultSet.getString(7)
										,resultSet.getString(8)
										,getNumberOfAnswersAvailable(resultSet.getLong(1))
										,resultSet.getLong(9)));
			}
			} catch (Exception e){
				log.info(e);
			} finally{
				try {
					resultSet.close();
					statement.close();
					connection.close();
				} catch (SQLException e) {
			}
		}
		return questions;
		}
	
	public static long addQuestion(Question question){
		log.info("Entered into DAO layer.");
		String query = "INSERT INTO QAP_question_tbl (user_id,question,question_doa,question_key1,question_key2,question_key3) VALUES (?,?,?,?,?,?);";	
		log.info(query);
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Statement statement = null;
		long questionId = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://192.168.35.70:3306/qaportal_dev","qaportal_dev","qaportal_dev");
			preparedStatement= connection.prepareStatement(query);
			preparedStatement.setLong(1,question.getUserId());
			preparedStatement.setString(2,question.getQuestion());
			preparedStatement.setString(3,question.getDateAdded());
			preparedStatement.setString(4,question.getTopic1());
			preparedStatement.setString(5,question.getTopic2());
			preparedStatement.setString(6,question.getTopic3());
			log.info("QuestionDAO layer.");
			int i = preparedStatement.executeUpdate();
			log.info("prepared Statement executed");
			if(i == 1){
				statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT question_id FROM QAP_question_tbl WHERE question = '"+question.getQuestion()+"';");
				while(resultSet.next()){
					questionId = resultSet.getLong(1);
				}
				resultSet.close();
				statement.close();
			}
			} catch (Exception e){
				log.info(e);
			} finally{
				try {
					preparedStatement.close();
					connection.close();
				} catch (SQLException e){
					log.info(e);
			}
		}
		log.info(questionId);
		return questionId;
	}

	public static List<Question> getQuestionsByUnAnswered(){
		List<Question> questions = new ArrayList<Question>();
		String query = "select q.* from QAP_question_tbl q left join QAP_answer_tbl a on q.question_id=a.question_id group by a.question_id order by count(a.answer_id); ";
		Connection connection = GetConnection.requestConnection();
		Statement statement = null ;
		ResultSet resultSet = null;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			while(resultSet.next()){
				questions.add(new Question(resultSet.getLong(1)
										,resultSet.getLong(2)
										,resultSet.getString(3)
										,resultSet.getString(4)
										,resultSet.getLong(5)
										,resultSet.getString(6)
										,resultSet.getString(7)
										,resultSet.getString(8)
										,getNumberOfAnswersAvailable(resultSet.getLong(1))
										,resultSet.getLong(9)));
			}
			} catch (Exception e){
				log.info(e);
			} finally{
				try {
					resultSet.close();
					statement.close();
					connection.close();
				} catch (SQLException e) {
			}
		}
		return questions;
		}
	public static long addParentIdForQuestion(long questionId, long parentId) {
		String query = "update QAP_question_tbl set question_pid = "+parentId+" where question_id = "+questionId+";";
		Connection connection = GetConnection.requestConnection();
		Statement statement = null;
		long result = 0;
		try {
			statement = connection.createStatement();
			result = statement.executeUpdate(query);
			} catch (Exception e){
				log.info(e);
			} finally{
				try {
					statement.close();
					connection.close();
				} catch (SQLException e) {
					log.info(e);
			}
		}
		return result;
	}
	
	public static boolean checkDuplicateOrNot(long questionId){
		String query = "select admin_status from QAP_question_tbl where question_id = "+questionId+";";
		Connection connection = GetConnection.requestConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		boolean result = false;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			while(resultSet.next()){
				result = resultSet.getBoolean(1);
			}
			} catch (Exception e){
				log.info(e);
			} finally{
				try {
					statement.close();
					connection.close();
				} catch (SQLException e) {
					log.info(e);
			}
		}
		return result;
	}
	public static List<Question> getRecentlyAskedQuestionsForSideView(){
		List<Question> questions = new ArrayList<Question>();
		Connection connection = GetConnection.requestConnection();
		String query = "SELECT * FROM QAP_question_tbl ORDER BY question_doa desc limit 0,5";
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			while(resultSet.next()){
				questions.add(new Question(resultSet.getLong(1)
						,resultSet.getLong(2)
						,resultSet.getString(3)
						,resultSet.getString(4)
						,resultSet.getLong(5)
						,resultSet.getString(6)
						,resultSet.getString(7)
						,resultSet.getString(8)
						,getNumberOfAnswersAvailable(resultSet.getLong(1))
						,resultSet.getLong(9)));
			}
		} catch (SQLException e){
			log.info(e);
		}
		try {
			resultSet.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			log.info(e);
		}
		return questions;
	}
}