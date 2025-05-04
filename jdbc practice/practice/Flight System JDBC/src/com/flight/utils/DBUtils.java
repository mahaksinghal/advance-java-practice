package com.flight.utils;
import java.sql.*;

public class DBUtils {
	private static Connection connection;
	private static final String DB_URL = "jdbc:mysql://localhost:3306/adv_java";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "root";
	
	public static Connection openConnection() throws SQLException {
		if(connection==null)
			connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
		return connection;
	}
	
	public static void closeConnection() throws SQLException {
		if(connection != null) {
			connection.close();
			connection = null;
		}	
	}	
}
