package com.cdac.utils;
import java.sql.*;

public class DBUtils {
	private static Connection connection;
	private static final String DB_URL = "jdbc:mysql://localhost:3306/dac2025";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "root";
	
	public static Connection openConnecion() throws ClassNotFoundException, SQLException {
		
		if(connection == null) {
			
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
		
		}
		return connection;
	}
	
}
