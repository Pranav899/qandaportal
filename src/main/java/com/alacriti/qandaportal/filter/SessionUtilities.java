package com.alacriti.qandaportal.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.alacriti.qandaportal.vo.User;

public class SessionUtilities {
	static Logger log = Logger.getLogger(SessionUtilities.class);
	public static String createSession(HttpServletRequest request, User user) {
		if(user.isLoginStatus()==true){
			HttpSession session = request.getSession();
			session.setAttribute("sessionObject", user);
		}
		return null;
		}
}
