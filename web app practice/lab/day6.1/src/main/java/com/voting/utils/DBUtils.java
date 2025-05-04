package com.voting.utils;

import java.sql.*;

public class DBUtils {
	private static Connection connection;
	
	// add a static method to create singleton db connection
	public static void openConnection(String dbURL,String userName,String password) throws /* ClassNotFoundException, */ SQLException {
		// lazy way of creating singleton instance of DB connection
		if (connection == null) {
			System.out.println("opening singleton cn");
			// 1. optional - load type IV JDBC driver in the method area
			// Class.forName("com.mysql.cj.jdbc.Driver");
			// 2. mandatory
			connection = DriverManager.getConnection
					(dbURL, userName, password);
		}
		
	}	
	//returns db connection.
	public static Connection getConnection() {
		return connection;
	}
	
	public static void closeConnection() throws SQLException {
		if (connection != null) {
			System.out.println("closing db cn");
			connection.close();
			connection = null;
		}
	}

}
