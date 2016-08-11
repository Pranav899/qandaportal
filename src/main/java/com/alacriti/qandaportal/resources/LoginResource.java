package com.alacriti.qandaportal.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import org.apache.log4j.Logger;

import com.alacriti.qandaportal.delegate.LoginDelegate;
import com.alacriti.qandaportal.filter.SessionFilter;

@Path("/login")
public class LoginResource{
	static Logger log = Logger.getLogger(LoginResource.class);
	
	@GET
	@Path("/home")
	public  String getHomePage(@Context HttpServletRequest request){
		return LoginDelegate.getHomePage(request);
	}
	
	@GET
	public String validateLogin(@Context HttpServletRequest request){
		if(SessionFilter.SESSION ==  true){
			return LoginDelegate.getHomePage(request);
		}
		else{
			return null;
		}
	}
	@POST
	public String validateLogin(@FormParam("userName") String userName,
			@FormParam("password") String password,
			@Context HttpServletRequest request){
		log.info("Login Resource Layer.");
		return LoginDelegate.validateLogin(userName,password,request);
	}
	
	@POST
	@Path("/mail")
	public String googleLogin(@FormParam("tokenId") String tokenId,@Context HttpServletRequest request){
		return LoginDelegate.googleLogin(tokenId,request);
	}
}
