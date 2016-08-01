package com.alacriti.qandaportal.bo;
import java.util.List;

import com.alacriti.qandaportal.dao.*;
import com.alacriti.qandaportal.vo.Question;

public class QuestionLogics {
	
	public static Question getQuestion(long questionId){
		return QuestionDAO.getQuestion(questionId);
	}
	
	public static List<Question> getRecentlyAskedQuestions(){
		return QuestionDAO.getRecentlyAskedQuestions();
	}
	
	public static List<Question> getMostViewedQuestions(){
		return QuestionDAO.getMostViewedQuestions();
	}
	
	public static List<Question> getQuestionsByTopic(String topic){
		return QuestionDAO.getQuestionsByTopic(topic);
	}
	
	public static void addQuestion(Question question){
		QuestionDAO.addQuestion(question);
	}
	
	
}
