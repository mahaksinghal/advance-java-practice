package com.cdac.tester;
import java.sql.*;
import static com.cdac.utils.DBUtils.openConnecion;

public class DisplayUserDetails {

	public static void main(String[] args) {
		try(Connection connection = openConnecion();
			Statement st = connection.createStatement();
			ResultSet rst = st.executeQuery("select * from emp");){		
			while(rst.next()) {
				System.out.println("Emp Id " + rst.getInt(1) + " Emp name " + rst.getString(1) + 
						" Job " + rst.getString(2) + " Hire Date " + rst.getDate(5) + 
						" Sal " + rst.getDouble(6) + " Dept No " + rst.getInt(8));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
