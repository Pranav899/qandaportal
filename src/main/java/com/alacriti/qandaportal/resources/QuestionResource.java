package com.alacriti.qandaportal.resources;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.alacriti.qandaportal.bo.QuestionLogics;
import com.alacriti.qandaportal.vo.*;

@Path("/question")
@Produces("application/json")
@Consumes("application/json")
public class QuestionResource{
	@POST
	public void addQuestion(Question question){
		QuestionLogics.addQuestion(question);
	}
	@GET
	@Path("/{questionId}")
	public Question getQuestion(@PathParam("questionId") long questionId){
		return QuestionLogics.getQuestion(questionId);
	}
	@GET
	@Path("/mostViewed")
	public List<Question> getMostViewedQuestions(){
		return QuestionLogics.getMostViewedQuestions();
	}
	
	@GET
	@Path("/recentlyAdded")
	public List<Question> getRecentlyAskedQuestions(){
		return QuestionLogics.getRecentlyAskedQuestions();
	}
	
	@GET
	@Path("/topic/{searchInput}")
	public List<Question> getQuestionsByTopic(@PathParam("searchInput") String searchInput){
		return QuestionLogics.getQuestionsByTopic(searchInput);
	}
	
	
}
