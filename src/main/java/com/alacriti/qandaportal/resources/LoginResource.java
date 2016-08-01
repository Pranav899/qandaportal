package com.alacriti.qandaportal.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.alacriti.qandaportal.delegate.LoginDelegate;
import com.alacriti.qandaportal.vo.Login;

@Path("/login")
public class LoginResource {
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Login validateLogin(Login login){
		return LoginDelegate.validateLogin(login);
	}
}
