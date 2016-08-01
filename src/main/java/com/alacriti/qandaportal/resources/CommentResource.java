package com.alacriti.qandaportal.resources;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.alacriti.qandaportal.bo.CommentLogics;
import com.alacriti.qandaportal.vo.Comment;

@Path("/comments")
@Produces("application/json")
@Consumes("application/json")
public class CommentResource {
	
	@GET
	@Path("/{questionId}/{answerId}")
	public ArrayList<Comment> getComments(@PathParam("questionId") long questionId, @PathParam("answerId") long answerId){
		return CommentLogics.getComments(questionId, answerId);
	}
	@POST
	public void addComment(Comment comment){
		CommentLogics.addComment(comment);
	}
}
