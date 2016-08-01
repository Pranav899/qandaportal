package com.alacriti.qandaportal.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.alacriti.qandaportal.bo.RatingLogics;
import com.alacriti.qandaportal.vo.*;

@Path("/rating")
@Produces("application/json")
@Consumes("application/json")
public class RatingResource{
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
	public void addRating(Rating rating){
		RatingLogics.addRating(rating);
	}
}
