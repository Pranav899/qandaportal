package com.alacriti.qandaportal.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.alacriti.qandaportal.delegate.GetConnection;
import com.alacriti.qandaportal.vo.Rating;

public class RatingDAO{
	static Logger log = Logger.getLogger(RatingDAO.class);
	public static long getSumOfPositiveRating(long questionId,long answerId){
		long positiveRating = 0;
		Connection connection = GetConnection.requestConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.createStatement();
			String query = "select count(rating) from QAP_rating_tbl where answer_id ="+answerId+" and question_id ="+questionId+" and rating = 1;";
			resultSet = statement.executeQuery(query);  
			while(resultSet.next()){
				positiveRating = resultSet.getLong(1);
			}
		} catch (SQLException e){
			log.info(e);
		} finally{
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
			}
		}
		return positiveRating;
	}
	public static long getSumOfNegativeRating(long questionId,long answerId){
		long negativeRating = 0;
		Connection connection = GetConnection.requestConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.createStatement();
			String query = "select count(rating) from QAP_rating_tbl where answer_id ="+answerId+" and question_id ="+questionId+" and rating = 0;";
			resultSet = statement.executeQuery(query);
			while(resultSet.next()){
				negativeRating = resultSet.getLong(1);
			}
		} catch (SQLException e){
			log.info(e);
		} finally{
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (SQLException e){
			}
		}
		log.info(negativeRating);
		return negativeRating;
	}
	public static void addRating(Rating rating){
		log.info("Entered to add rating DAO");
		String queryForCheck = "select count(rating) from QAP_rating_tbl where question_id = "+rating.getQuestionId()+" and answer_id = "+rating.getAnswerId()+" and user_id = "+rating.getUserId()+";";
		Connection connection = GetConnection.requestConnection();
		Statement statement = null;
		Statement statement2 = null;
		ResultSet resultSet = null;
		long present = 0;
		try {
			log.info("Entered to try block.");
			statement = connection.createStatement();
			resultSet = statement.executeQuery(queryForCheck);
			while(resultSet.next()){
				present = resultSet.getLong(1);
			}
			if(present == 0){
				log.info("Entered to if.");
				String query = "INSERT QAP_rating_tbl VALUES ("+rating.getAnswerId()+","+rating.getQuestionId()+","+rating.getUserId()+","+rating.getRating()+");";
				log.info(query);
				statement2 = connection.createStatement();
				statement2.executeUpdate(query);
			}
			else{
				log.info("Entered to else");
				String query = "UPDATE QAP_rating_tbl SET rating = "+rating.getRating()+" WHERE question_id = "+rating.getQuestionId()+" AND answer_id = "+rating.getAnswerId()+" AND user_id = "+rating.getUserId()+";";
				statement2 = connection.createStatement();
				statement2.executeUpdate(query);
			}
		} catch (SQLException e){
			log.info(e);
		} finally{
			try {
				statement.close();
				statement2.close();
				connection.close();
			} catch (SQLException e){
			}
		}
	}
}