package com.voting.utils;
import java.sql.*;

public class DBUtils {
	private static Connection connection;
	//write a static method to establish connection to DB
	public static void openConnection(String dbURL,
			String userName,String pwd) 
			throws SQLException{
	//	String dbURL="jdbc:mysql://localhost:3306/advjava";
		/*
		 * API of java.sql.DriverManager
		 * public static Connection getConnection(String url ,
		 * String userName,String password) throws SQLException
		 */
		connection=DriverManager.getConnection
				(dbURL,userName,pwd);
		System.out.println("DB connection opened!");
		
	}
	//add a static method to close db connection
	public static void closeConnection() throws SQLException{
		if(connection != null)
			connection.close();
		System.out.println("DB connection closed!");
	}
	//add a static method to get existing connection
	public static Connection getConnection() {
		return connection;
	}

}
