package com.alacriti.qandaportal.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.alacriti.qandaportal.delegate.GetConnection;
import com.alacriti.qandaportal.vo.Answer;
import com.alacriti.qandaportal.vo.Comment;

public class CommentDAO {
	static Logger log = Logger.getLogger(CommentDAO.class);
	public static ArrayList<Comment> getComments(long questionId, long answerId){
		ArrayList<Comment> comments = new ArrayList<Comment>();
		String query = "select * from QAP_comments_tbl where answer_id ="+answerId+" and question_id ="+questionId+" order by comment_id desc;";
		log.info(query);
		Connection connection = GetConnection.requestConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			while(resultSet.next()){
				comments.add(new Comment(resultSet.getLong(1),resultSet.getLong(2),resultSet.getLong(3),resultSet.getLong(4),resultSet.getString(5)));
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
		return comments;
	}
	public static void addComment(Comment comment){
		String query ="INSERT INTO QAP_comments_tbl (answer_id, question_id, user_id,comments) VALUES ("+comment.getAnswerid()+","+comment.getQuestionId()+","+comment.getuserId()+","+comment.getComment()+");";
		Connection connection = GetConnection.requestConnection();
		Statement statement = null;
		try {
			statement = connection.createStatement();
			statement.executeUpdate(query);
			} catch (Exception e){
				log.info(e);
			} finally{
				try {
					statement.close();
					connection.close();
				} catch (SQLException e){
					log.info(e);
			}
		}
	}
}
