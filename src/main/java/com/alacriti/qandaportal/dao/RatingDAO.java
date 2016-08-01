package com.alacriti.qandaportal.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.alacriti.qandaportal.delegate.GetConnection;
import com.alacriti.qandaportal.vo.Rating;

public class RatingDAO {
	static Logger log = Logger.getLogger(RatingDAO.class);
	public static long getSumOfPositiveRating(long questionId,long answerId){
		long positiveRating = 0;
		Connection connection = GetConnection.requestConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.createStatement();
			String query = "select count(rating) from QAP_rating_tbl where answer_id="+answerId+"and question_id ="+questionId+" and rating = 1;";
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
			String query = "select count(rating) from QAP_rating_tbl where answer_id="+answerId+"and question_id ="+questionId+" and rating = 0;";
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
		return negativeRating;
	}
	public static void addRating(Rating rating){
		String query = "INSERT QAP answer_rating_tbl VALUES ("+rating.getAnswerId()+","+rating.getQuestionId()+","+rating.getUserId()+","+rating.getRating()+");";
		Connection connection = GetConnection.requestConnection();
		Statement statement = null;
		try {
			statement = connection.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e){
			log.info(e);
		} finally{
			try {
				statement.close();
				connection.close();
			} catch (SQLException e){
			}
		}
	}
}
