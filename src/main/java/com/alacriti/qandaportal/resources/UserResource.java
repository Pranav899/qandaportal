package com.alacriti.qandaportal.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.alacriti.qandaportal.bo.UserLogics;
import com.alacriti.qandaportal.vo.*;

@Path("/user")
@Produces("application/json")
@Consumes("application/json")
public class UserResource{
	
	@GET
	public User validateUser(){
		return UserLogics.validateUser();
	}
	@POST
	public void addUser(){
		return ;
	}
}
