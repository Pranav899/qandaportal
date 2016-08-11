package com.alacriti.qandaportal.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

import com.alacriti.qandaportal.bo.CommentLogics;
import com.alacriti.qandaportal.delegate.CommentDelegate;
import com.alacriti.qandaportal.vo.Comment;
import com.alacriti.qandaportal.vo.User;

@Path("/comments")
public class CommentResource {
	@GET
	@Path("/{questionId}/{answerId}")
	public String getComments(@PathParam("questionId") long questionId, @PathParam("answerId") long answerId){
		return CommentDelegate.getComments(questionId, answerId);
	}
	@POST
	@Path("/add/{questionId}/{answerId}")
	public String addComment(@PathParam("questionId") long questionId, 
							 @PathParam("answerId") long answerId,
							 @FormParam("comment") String comment,@Context HttpServletRequest request){
		User sessionUser = (User) request.getSession().getAttribute("sessionObject");
		CommentLogics.addComment(new Comment(answerId,questionId,sessionUser.getUserId(),comment));
		return CommentDelegate.getComments(questionId, answerId);
	}
}
