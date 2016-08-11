package com.alacriti.qandaportal.resources;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

import org.apache.log4j.Logger;

import com.alacriti.qandaportal.bo.QuestionLogics;
import com.alacriti.qandaportal.delegate.QuestionDelegate;
import com.alacriti.qandaportal.vo.*;

@Path("/question")
public class QuestionResource{
	static Logger log = Logger.getLogger(QuestionResource.class);
	@POST
	@Path("/add")
	public String addQuestion(@FormParam("question") String askedQuestion,
			@FormParam("keyword1") String keyword1,
			@FormParam("keyword2") String keyword2,
			@FormParam("keyword3") String keyword3,@Context HttpServletRequest request){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(new Date());
		User sessionUser = (User) request.getSession().getAttribute("sessionObject");
		Question question = new Question(sessionUser.getUserId(),askedQuestion,date,keyword1,keyword2,keyword3);
		return QuestionDelegate.addQuestion(question);
	}
	
	@GET
	@Path("/addQuestionPage")
	public String addQuestionPage(){
		return QuestionDelegate.addQuestionPage();
	}
	
	@GET
	@Path("/{questionId}")
	public Question getQuestion(@PathParam("questionId") long questionId){
		return QuestionLogics.getQuestion(questionId);
	}
	
	@GET
	@Path("/mostViewed")
	public String getMostViewedQuestions(){
		return QuestionDelegate.getMostViewedQuestions();
	}
	
	@GET
	@Path("/recentlyAdded")
	public String getRecentlyAskedQuestions(){
		return QuestionDelegate.getRecentlyAskedQuestions();
	}
	
	@GET
	@Path("/recentlyAdded/{uniqueId}/{start}/{noOfPages}")
	public String getRecentlyAddedQuestions(@PathParam("uniqueId") long uniqueId,@PathParam("start") long start,@PathParam("noOfPages") long noOfPages){
		return QuestionDelegate.getRecentlyAddedQuestions(uniqueId,start,noOfPages);
	}
	@GET
	@Path("/recentlyAddedQuestionForSideView")
	public String getRecentlyAskedQuestionsForSideView(){
		return QuestionDelegate.getRecentlyAskedQuestionsForSideView();
	}
	
	@GET
	@Path("/topic/{searchItem}")
	public String getQuestionsByTopic(@PathParam("searchItem") String searchItem){
		return QuestionDelegate.getQuestionsByTopic(searchItem);
	}
	
	@GET
	@Path("/search/topic/{searchItem}")
	public String getQuestionsBySearch(@PathParam("searchItem") String searchItem){
		return QuestionDelegate.getQuestionsBySearch(searchItem);
	}
	
	@GET
	@Path("/unAnswered")
	public String getQuestionsByUnAnswered(){
		return QuestionDelegate.getQuestionsByUnAnswered();
	}
	
	@POST
	@Path("/{questionId}/parent")
	public String addParentIdForAQuestion(@PathParam("questionId") long questionId,@FormParam("parentId") long parentId){
		return QuestionDelegate.addParentIdForQuestion(questionId,parentId);
	}
}
