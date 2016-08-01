package com.alacriti.qandaportal.bo;


import java.util.List;

import com.alacriti.qandaportal.dao.AnswerDAO;
import com.alacriti.qandaportal.vo.Answer;

public class AnswerLogics {
	
	public static List<Answer> getAnswersByRating(long questionId){
		return AnswerDAO.getAnswersByRating(questionId);
	}
	public static void addAnswer(Answer answer){
		AnswerDAO.addAnswer(answer);
	}
}
