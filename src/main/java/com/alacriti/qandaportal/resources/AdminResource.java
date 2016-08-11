package com.alacriti.qandaportal.resources;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.apache.log4j.Logger;
import com.alacriti.qandaportal.bo.AdminLogics;
import com.alacriti.qandaportal.delegate.AdminDelegate;

@Path("/admin")
public class AdminResource {
	static Logger log = Logger.getLogger(AdminResource.class);
	@GET
	@Path("/list")
	public static String getQuestionsForConfirming(){
		return AdminDelegate.getQuestionsForConfirming();
	}
	
	@GET
	@Path("/{questionId}")
	public  String getQuestionForView(@PathParam("questionId") long questionId){
		return AdminDelegate.getQuestionForView(questionId);
	}
	
	@POST
	@Path("/confirm/{questionId}/{parentId}")
	public void conformParentId(@PathParam("questionId") long questionId, @PathParam("parentId") long parentId){
		long result = AdminLogics.confirmStatus(questionId,parentId);
	}
	
	@POST
	@Path("/deny/{questionId}/{parentId}")
	public void denyParentId(@PathParam("questionId") long questionId, @PathParam("parentId") long parentId){
		long result = AdminLogics.denyStatus(questionId,parentId);
	}
}
