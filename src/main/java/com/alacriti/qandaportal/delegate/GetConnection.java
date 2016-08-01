package com.alacriti.qandaportal.delegate;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.log4j.Logger;

import com.alacriti.qandaportal.dao.QuestionDAO;


public class GetConnection {
	static Logger log = Logger.getLogger(GetConnection.class);
	public static Connection requestConnection(){
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://192.168.35.70:3306/qaportal_dev","qaportal_dev","qaportal_dev");
			log.info("Connection created.");
		} catch (Exception e) {
			log.info(e);
		}
		return connection;
	}
}
