package com.alacriti.qandaportal.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import com.alacriti.qandaportal.delegate.GetConnection;
import com.alacriti.qandaportal.vo.Question;

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
					log.info("Connection Closed.");
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
				log.info("Connection Closed.");
			} catch (SQLException e){
				log.info(e);
			}
		}
		increaseNumberOfViews(questionId);
		return question;
	}
	public static List<Question> getRecentlyAskedQuestions(){
		List<Question> questions = new ArrayList<Question>();
		Connection connection = GetConnection.requestConnection();
		String query = "SELECT * FROM QAP_question_tbl ORDER BY question_doa desc";
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
	
	public static List<Question> getMostViewedQuestions(){
		List<Question> questions = new ArrayList<Question>();
		String query = "SELECT * FROM QAP_question_tbl ORDER BY question_no_of_views desc";
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
				System.out.println(e);
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
		log.info(query);
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
				System.out.println(e);
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
	
	public static void addQuestion(Question question){
		String query = "INSERT INTO QAP_question_tbl VALUES ("+question.getQuestionId()+","+
															   question.getUserId()+",'"+
															   question.getQuestion()+"','"+
															   question.getDateAdded()+"',"+
															   question.getParentQuestionId()+",'"+
															   question.getTopic1()+"','"+
															   question.getTopic2()+"','"+
															   question.getTopic3()+"',"+
															   question.getViews()+
															");";
		log.info(query);
		Connection connection = null;
		Statement statement = null ;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://192.168.35.70:3306/qaportal_dev","qaportal_dev","qaportal_dev");
			statement = connection.createStatement();
			statement.executeUpdate(query);
			} catch (Exception e){
			} finally{
				try {
					statement.close();
					connection.close();
				} catch (SQLException e){
			}
		}
	}
}