package com.alacriti.qandaportal.resources;

import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.alacriti.qandaportal.delegate.UserDelegate;

@Path("/user")
public class UserResource{
	static Logger log = Logger.getLogger(UserResource.class);
	@POST
	@Path("/register")
	public String addUser(@FormParam("userName") String userName,@FormParam("fullName") String fullName,@FormParam("email") String email
			,@FormParam("password") String password,@Context HttpServletRequest request){
		return UserDelegate.addUser(userName,fullName,email,password,request);
	}
	
	@GET
	@Path("/logout")
	public Response logoutUser(@Context HttpServletRequest request){
		HttpSession session=request.getSession(false);
		session.invalidate();
		URI uri = null;
		try {
			uri = new URI("http://localhost:8080/qandaportal/login.html");
		} catch (URISyntaxException e){
			log.info(e);
		}
		return Response.seeOther(uri).build();
	}
}
