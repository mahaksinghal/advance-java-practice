package com.flights.utils;

import java.sql.Connection;
import java.sql.*;

public class DBUtils {
	private static Connection connection;
	private static final String URL = "jdbc:mysql://localhost:3306/adv_java";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "root";
	
	public static void openConnection() throws SQLException {
		System.out.println("in openConnection");
		if(connection == null) {
			connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
		}
	}
	
	public static Connection getConnection() {
		System.out.println("in getConnection");
		return connection;
	}
	
	public static void closeConnection() throws SQLException{
		System.out.println("in closeConnection");
		if(connection != null) {
			connection.close();
			connection = null;
		}
	}

}
