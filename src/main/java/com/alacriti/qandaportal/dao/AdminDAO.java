package com.alacriti.qandaportal.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.alacriti.qandaportal.delegate.GetConnection;
import com.alacriti.qandaportal.vo.Question;

public class AdminDAO {
	public static List<Question> getQuestionsForConfirming(){
		List<Question> questions = new ArrayList<Question>();
		Connection connection = GetConnection.requestConnection();
		String query = "select * from QAP_question_tbl where question_pid is not null and admin_status is null;";
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
										,QuestionDAO.getNumberOfAnswersAvailable(resultSet.getLong(1))
										,resultSet.getLong(9)));
			}
			} catch (Exception e){
				
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

	public static long confirmStatus(long questionId, long parentId) {
		long result = 0;
		Connection connection = GetConnection.requestConnection();
		String query = "update QAP_question_tbl set admin_status = true where question_id = "+questionId+" and question_pid = "+parentId+";";
		Statement statement = null;
		try {
			statement = connection.createStatement();
			result = statement.executeUpdate(query);
			} catch (Exception e){
				
			} finally{
				try {
					statement.close();
					connection.close();
				} catch (SQLException e) {
			}
		}
		return result;
	}

	public static long denyStatus(long questionId, long parentId) {
		long result = 0;
		Connection connection = GetConnection.requestConnection();
		String query = "update QAP_question_tbl set admin_status = false where question_id = "+questionId+" and question_pid = "+parentId+";";
		Statement statement = null;
		try {
			statement = connection.createStatement();
			result = statement.executeUpdate(query);
			} catch (Exception e){
				
			} finally{
				try {
					statement.close();
					connection.close();
				} catch (SQLException e) {
			}
		}
		return result;
	}
}
