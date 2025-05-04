package com.cdac.utils;

import java.sql.*;

public class DBUtils {
	private static Connection connection;
	private static final String DB_URL = "jdbc:mysql://localhost:3306/adv_java";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "root";

	// add a static method to open n return singleton db connection
	public static Connection openConnection() throws /* ClassNotFoundException, */ SQLException {
		// lazy way of creating singleton instance of DB connection
		if (connection == null) {
			System.out.println("opening singleton cn");
			// 1. optional - load type IV JDBC driver in the method area
			// Class.forName("com.mysql.cj.jdbc.Driver");
			// 2. mandatory
			connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
		}
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
