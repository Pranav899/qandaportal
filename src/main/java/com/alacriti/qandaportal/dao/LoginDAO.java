package com.alacriti.qandaportal.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.alacriti.qandaportal.vo.Login;
import com.alacriti.qandaportal.vo.User;

public class LoginDAO{
		static Logger log = Logger.getLogger(LoginDAO.class);
		public static String getLoginData(Login login){
			Connection connection = null;
			Statement statement = null ;
			ResultSet resultSet = null;
			String password = null;
			String query = "SELECT user_pwd FROM QAP_user_profile_tbl WHERE user_name = '"+login.getUsername()+"';";
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://192.168.35.70:3306/qaportal_dev","qaportal_dev","qaportal_dev");
				statement = connection.createStatement();
				resultSet = statement.executeQuery(query);
				while(resultSet.next()){
					password = resultSet.getString("user_pwd");
				}
			} catch (Exception e){
				
			} finally{
				try {
					resultSet.close();
					statement.close();
					connection.close();
				} catch (SQLException e) {
					
				}
			}
			return password;
		}
		
		public static User getUserData(String userName){
			Connection connection = null;
			Statement statement = null ;
			ResultSet resultSet = null;
			String query = "SELECT * FROM QAP_user_profile_tbl WHERE user_name = '"+userName+"';";
			User user = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://192.168.35.70:3306/qaportal_dev","qaportal_dev","qaportal_dev");
				statement = connection.createStatement();
				resultSet = statement.executeQuery(query);
				while(resultSet.next()){
					user = new User(resultSet.getLong(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4), resultSet.getString(5),
							resultSet.getString(6));
				}
			} catch (Exception e){
				
			} finally{
				try {
					resultSet.close();
					statement.close();
					connection.close();
				} catch (SQLException e) {
					
				}
			}
			return user;
		}
		
		public static long AddGoogleUserData(String userName,String userFullName,String userEmail){
			Connection connection = null; 
			Statement statement = null ;
			ResultSet resultSet = null;
			String query = "select exists(select * from QAP_user_profile_tbl where user_email = '"+userEmail+"');";
			log.info(query);
			long exist = 0;
			long cleared = 0;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://192.168.35.70:3306/qaportal_dev","qaportal_dev","qaportal_dev");
				statement = connection.createStatement();
				resultSet = statement.executeQuery(query);
				while(resultSet.next()){
					exist = resultSet.getLong(1);
					log.info(resultSet.getLong(1));
				}
				if(exist == 0){
					log.info("If condition");
					String insertQuery = "INSERT INTO QAP_user_profile_tbl (user_name,user_full_name,user_email,user_role) values ('"+userName+"','"+userFullName+"','"+userEmail+"','user');";
					log.info(insertQuery);
					cleared = statement.executeUpdate(insertQuery);
					log.info(cleared);
				}
				if(exist == 1){
					log.info(exist);
					cleared = 1;
				}
			} catch (Exception e){
				log.info(e);
			} finally{
				try {
					resultSet.close();
					statement.close();
					connection.close();
				} catch (SQLException e){
					
				}
			}
			log.info(cleared);
			return cleared;
		}
}