package com.alacriti.qandaportal.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.alacriti.qandaportal.bo.RatingLogics;
import com.alacriti.qandaportal.delegate.GetConnection;
import com.alacriti.qandaportal.vo.Answer;
import com.alacriti.qandaportal.vo.Question;

public class AnswerDAO{
	static Logger log = Logger.getLogger(AnswerDAO.class);
	public static List<Answer> getAnswersByRating(long questionId){
		List<Answer> answers = new ArrayList<Answer>();
		String query = "SELECT a.answer_id,a.question_id,a.user_id,a.answer,count(r.rating) from QAP_answer_tbl a left join QAP_rating_tbl r on a.answer_id=r.answer_id group by a.answer_id having a.question_id="+questionId+" order by count(r.rating) desc;";
		Connection connection = GetConnection.requestConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			while(resultSet.next()){
				long positiveRating = RatingLogics.getSumOfPositiveRating(questionId, resultSet.getLong(1));
				long negativeRating = RatingLogics.getSumOfNegativeRating(questionId, resultSet.getLong(1));
				answers.add(new Answer(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),resultSet.getString(4),positiveRating,negativeRating));
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
		return answers;
	}
	public static long addAnswer(Answer answer){
		String query = "INSERT INTO QAP_answer_tbl  (question_id,user_id,answer) VALUES ("+answer.getQuestionId()+","+answer.getUserId()+",'"+answer.getAnswer()+"');";
		Connection connection = GetConnection.requestConnection();
		Statement statement = null ;
		long status = 0;
		log.info(query);
		try {
			statement = connection.createStatement();
			status = statement.executeUpdate(query);
			}catch (Exception e){
				log.info(e);
			}finally{
				try {
					statement.close();
					connection.close();
				} catch (SQLException e){
					log.info(e);
			}
		}
		return status;
	}
	public static Answer getAnswer(long questionId, long answerId) {
		Answer answer = null;
		String query = "SELECT * FROM QAP_answer_tbl WHERE question_id = "+questionId+" AND answer_id = "+answerId+";";
		Connection connection = GetConnection.requestConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			while(resultSet.next()){
				long positiveRating = RatingLogics.getSumOfPositiveRating(questionId, resultSet.getLong(1));
				long negativeRating = RatingLogics.getSumOfNegativeRating(questionId, resultSet.getLong(1));
				answer = new Answer(resultSet.getLong(1),resultSet.getLong(2),resultSet.getLong(3),resultSet.getString(4),positiveRating,negativeRating);
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
		return answer;
	}
	public static List<Answer> getAnswersBySearch(String question,String searchInput){
		List<Answer> answers = new ArrayList<Answer>();
		String query = "select * from QAP_answer_tbl where question_id=(select question_id from QAP_question_tbl where question = '"+question+"') and  answer like '%"+searchInput+"%';";
		Connection connection = GetConnection.requestConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			while(resultSet.next()){
				long positiveRating = RatingLogics.getSumOfPositiveRating(resultSet.getLong(2), resultSet.getLong(1));
				long negativeRating = RatingLogics.getSumOfNegativeRating(resultSet.getLong(3), resultSet.getLong(1));
				answers.add(new Answer(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),resultSet.getString(4),positiveRating,negativeRating));
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
		return answers;
	}
}
