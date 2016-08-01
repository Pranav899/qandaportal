package com.alacriti.qandaportal.delegate;

import com.alacriti.qandaportal.bo.LoginLogics;
import com.alacriti.qandaportal.vo.Login;

public class LoginDelegate {
	
	public static Login validateLogin(Login login){
			return LoginLogics.validateLogin(login);
	}
}