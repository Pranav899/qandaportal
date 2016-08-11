package com.alacriti.qandaportal.bo;

import org.apache.log4j.Logger;

import com.alacriti.qandaportal.dao.LoginDAO;
import com.alacriti.qandaportal.vo.Login;

public class LoginLogics {
	static Logger log = Logger.getLogger(LoginLogics.class);
	public static boolean validateLogin(Login login){
		String password = LoginDAO.getLoginData(login);
		if(login.getPassword().equals(password)){
			return true;
		}
		else{
			return false;
		}
	}
}