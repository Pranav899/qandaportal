package com.alacriti.qandaportal.bo;

import org.apache.log4j.Logger;

import com.alacriti.qandaportal.dao.UserDAO;
import com.alacriti.qandaportal.delegate.UserDelegate;
import com.alacriti.qandaportal.vo.User;

public class UserLogics {
	static Logger log = Logger.getLogger(UserDelegate.class);
	public static boolean addUser(String userName, String fullName, String email, String password){
		User user = new User(userName,fullName,email,password,"user");
		long result = UserDAO.addUser(user);
		log.info(result);
		if(result == 1){
			return true;
		}
		else{
			return false;
		}
	}
}
