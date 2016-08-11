package com.alacriti.qandaportal.delegate;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.alacriti.qandaportal.bo.UserLogics;

public class UserDelegate {
	static Logger log = Logger.getLogger(UserDelegate.class);
	public static String addUser(String userName, String fullName,
			String email, String password,HttpServletRequest request) {
		boolean result = UserLogics.addUser(userName,fullName,email,password);
		if(result == true){
			return LoginDelegate.validateLogin(userName, password,request);
		}
		else{
			return "Failed";
		}
	}
		
}
