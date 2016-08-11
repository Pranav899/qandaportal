package com.alacriti.qandaportal.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.alacriti.qandaportal.vo.User;

public class UserDAO {
	static Logger log = Logger.getLogger(UserDAO.class);
	public static long addUser(User user){
		ResultSet resultSet = null;
		Connection connection = null;
		Statement statement = null ;
		long result = 0;
		try {
			log.info("try block");
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://192.168.35.70:3306/qaportal_dev","qaportal_dev","qaportal_dev");
			statement = connection.createStatement();
			String check = "select count(user_id) from QAP_user_profile_tbl where user_email = '"+user.getEmail()+"';";
			log.info(check);
			resultSet = statement.executeQuery(check);
			log.info(resultSet);
			while(resultSet.next()){
				log.info(resultSet.getLong(1));
				if(resultSet.getLong(1) == 0){
					log.info(resultSet.getLong(1));
					String query = "insert into QAP_user_profile_tbl (user_name,user_full_name,user_email,user_pwd,user_role) values ('"+user.getUserName()+"','"+user.getFullName()+"','"+user.getEmail()+"','"+user.getPassword()+"','"+user.getRole()+"');";
					log.info(query);
					result = statement.executeUpdate(query);
				}
			}
		} catch (Exception e){
			log.info(e);
		} finally{
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				log.info(e);
			}
		}
		return result;
	}
}
