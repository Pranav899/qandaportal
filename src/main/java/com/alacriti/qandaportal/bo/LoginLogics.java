package com.alacriti.qandaportal.bo;

import java.util.Iterator;
import java.util.List;
import com.alacriti.qandaportal.dao.LoginDAO;
import com.alacriti.qandaportal.vo.Login;

public class LoginLogics {
	
	public static Login validateLogin(Login login){
		List<Login> logins = LoginDAO.getLoginData();
		Iterator<Login> iterator = logins.iterator();
		boolean valid = false;
		while(iterator.hasNext()){
			Login newLogin = iterator.next();
			if((newLogin.getUsername() == login.getUsername()) && (newLogin.getPassword() == login.getPassword())){
				valid = true;
				break;
			}
		}
		if(valid == true){
			return login;
		}
		else{
			return new Login("pavan","kumar");
		}
	}
}