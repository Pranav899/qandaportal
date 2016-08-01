package com.alacriti.qandaportal.dao;

import java.sql.ResultSet;

import com.alacriti.qandaportal.vo.User;

public class UserDAO {
		
	public static User validateUser(){
		String query = "query";
		ResultSet resultSet = DataResource.getData(query);
		User user = new User("Pavan",1L,"Pranav Pavan Kumar","Pavan@899","pranav.surekha@gmail.com");
		return user;
	}
	
	public static void addUser(){
		String query = "";
		ResultSet resultSet = DataResource.getData(query);
		return ;
	}
}
