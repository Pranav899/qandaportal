package com.alacriti.qandaportal.bo;
import java.util.List;

import com.alacriti.qandaportal.dao.*;
import com.alacriti.qandaportal.vo.Question;
import com.alacriti.qandaportal.vo.QuestionPage;

public class QuestionLogics {
	
	public static Question getQuestion(long questionId){
		return QuestionDAO.getQuestion(questionId);
	}
	
	public static QuestionPage getRecentlyAskedQuestions(){
		return QuestionDAO.getRecentlyAskedQuestions();
	}
	public static List<Question> getRecentlyAskedQuestionForSideView(){
		return QuestionDAO.getRecentlyAskedQuestionsForSideView();
	}
	public static List<Question> getMostViewedQuestions(){
		return QuestionDAO.getMostViewedQuestions();
	}
	
	public static List<Question> getQuestionsByTopic(String topic){
		return QuestionDAO.getQuestionsByTopic(topic);
	}
	
	public static long addQuestion(Question question){
		return QuestionDAO.addQuestion(question);
	}

	public static List<Question> getQuestionsByUnAnswered() {
		return QuestionDAO.getQuestionsByUnAnswered();
	}

	public static long addParentIdForQuestion(long questionId, long parentId){
		return QuestionDAO.addParentIdForQuestion(questionId,parentId);
	}

	public static QuestionPage getRecentlyAskedQuestions(long uniqueId,
			long start,long noOfPages) {
		return QuestionDAO.getRecentlyAskedQuestions(uniqueId,start,noOfPages);
	}
	
}
