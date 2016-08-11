package com.alacriti.qandaportal.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataResource{
	
	public static ResultSet getData(String query){
		ResultSet resultSet = null ;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.35.70:3306/qaportal_dev","qaportal_dev","qaportal_dev");
			Statement statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
		} catch (Exception e){
			
		} finally{
			
		}
		return resultSet;
	}
}
