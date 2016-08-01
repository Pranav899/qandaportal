package com.alacriti.qandaportal.bo;

import java.util.ArrayList;

import com.alacriti.qandaportal.dao.CommentDAO;
import com.alacriti.qandaportal.vo.Comment;

public class CommentLogics {
	
	public static ArrayList<Comment> getComments(long questionId, long answerId){
		return CommentDAO.getComments(questionId, answerId);
	}
	
	public static void addComment(Comment comment){
		CommentDAO.addComment(comment);
	}
}
