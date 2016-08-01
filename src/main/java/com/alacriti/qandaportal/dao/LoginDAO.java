package com.alacriti.qandaportal.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.alacriti.qandaportal.vo.Login;

public class LoginDAO{
		static Logger log = Logger.getLogger(LoginDAO.class.getName());
		public static List<Login> getLoginData(){
			Connection connection = null;
			Statement statement = null ;
			ResultSet resultSet = null;
			String query = "SELECT user_name, user_pwd FROM QAP_user_profile_tbl";
			List<Login> logins = new ArrayList<Login>();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://192.168.35.70:3306/qaportal_dev","qaportal_dev","qaportal_dev");
				statement = connection.createStatement();
				resultSet = statement.executeQuery(query);
				while(resultSet.next()){
					String username = resultSet.getString("user_name");
					String password = resultSet.getString("user_pwd");
					logins.add(new Login(username,password));
					log.info("added");
				}
			} catch (Exception e){
				System.out.println(e);
			} finally{
				try {
					resultSet.close();
					statement.close();
					connection.close();
				} catch (SQLException e) {
					
				}
			}
			return logins;
		}
}