package com.alacriti.qandaportal.bo;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.alacriti.qandaportal.dao.AnswerDAO;
import com.alacriti.qandaportal.delegate.AnswerDelegate;
import com.alacriti.qandaportal.vo.Answer;

public class AnswerLogics {
	
	public static List<Answer> getAnswersByRating(long questionId){
		return AnswerDAO.getAnswersByRating(questionId);
	}
	public static String addAnswer(Answer answer, HttpServletRequest request){
		return AnswerDelegate.addAnswer(answer);
	}
	public static Answer getAnswer(long questionId, long answerId) {
		return AnswerDAO.getAnswer(questionId,answerId);
	}
	public static List<Answer> getAnswersBySearch(String question,String searchInput) {
		return AnswerDAO.getAnswersBySearch(question,searchInput);
	}
}
