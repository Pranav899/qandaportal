package com.alacriti.qandaportal.resources;

import java.io.UnsupportedEncodingException;

import com.alacriti.qandaportal.vo.*;
import com.alacriti.qandaportal.bo.*;
import com.alacriti.qandaportal.delegate.AnswerDelegate;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.apache.log4j.Logger;

@Path("/answer")
public class AnswerResource {
	static Logger log = Logger.getLogger(AnswerResource.class);
	
	@GET
	@Path("/search/{question}/{searchInput}")
	public String getAnswersBySearch(@PathParam("question") String question,@PathParam("searchInput") String searchInput){
		String result = null;
		try {
			result = java.net.URLDecoder.decode(question, "UTF-8");
		} catch (UnsupportedEncodingException e) {
		}
		return AnswerDelegate.getAnswersBySearch(result,searchInput);
	}
	
	@GET
	@Path("/{questionId}")
	public String getAnswersByRating(@PathParam("questionId") long questionId){
		return AnswerDelegate.getAnswersByRating(questionId);
	}
	
	@Path("/{questionId}/{answerId}")
	public Answer getAnswer(@PathParam("questionId") long questionId, @PathParam("answerId") long answerId){
		return AnswerLogics.getAnswer(questionId,answerId);
	}
	
	@GET
	@Path("/{questionId}/addPage")
	public String addAnswerPage(@PathParam("questionId")long questionId){
		return AnswerDelegate.addAnswerPage(questionId);
	}
	
	@POST
	@Path("/{questionId}/add")
	public String addAnswer(@FormParam("answer") String answer,
							@PathParam("questionId") long questionId,
							@Context HttpServletRequest request){
		User sessionUser = (User) request.getSession().getAttribute("sessionObject");
		return AnswerLogics.addAnswer(new Answer(questionId,sessionUser.getUserId(),answer),request);
	}
}
