package com.alacriti.qandaportal.resources;


import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.apache.log4j.Logger;

import com.alacriti.qandaportal.bo.RatingLogics;
import com.alacriti.qandaportal.vo.AnswerRating;

@Path("/rating")
public class RatingResource{
	static Logger log = Logger.getLogger(RatingResource.class);
	@GET
	@Path("/positive/{questionId}/{answerId}")
	public long RatingPositive(@PathParam("questionId") long questionId,@PathParam("answerId") long answerId){
		return RatingLogics.getSumOfPositiveRating(questionId, answerId);
	}
	@GET
	@Path("/negative/{questionId}/{answerId}")
	public long RatingNegative(@PathParam("questionId") long questionId,@PathParam("answerId") long answerId){
		return RatingLogics.getSumOfNegativeRating(questionId, answerId);
	}
	@POST
	@Path("/add/{questionId}/{answerId}/{rating}")
	@Produces("application/json")
	public AnswerRating addRatingPositive(@PathParam("questionId") long questionId,
								  @PathParam("answerId") long answerId,
								  @PathParam("rating") long rating,@Context HttpServletRequest request){
		return RatingLogics.addRating(questionId,answerId,rating,request);
	}
}
