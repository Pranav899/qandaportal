package com.alacriti.qandaportal.resources;

import java.util.List;

import com.alacriti.qandaportal.vo.*;
import com.alacriti.qandaportal.bo.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/answer")
@Produces("application/json")
@Consumes("application/json")
public class AnswerResource {
	
	@GET
	@Path("/{questionId}")
	public List<Answer> getAnswersByRating(@PathParam("questionId") long questionId){
		return AnswerLogics.getAnswersByRating(questionId);
	}
	@POST
	public void addAnswer(Answer answer){
		AnswerLogics.addAnswer(answer);
	}
}
