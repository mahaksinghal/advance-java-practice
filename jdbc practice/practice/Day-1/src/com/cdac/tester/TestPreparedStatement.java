package com.cdac.tester;
import java.sql.*;
import java.util.Scanner;

import static com.cdac.utils.DBUtils.openConnecion;

public class TestPreparedStatement {

	public static void main(String[] args) {
		String sql = "select empno, ename, hiredate, job, deptno from emp where deptno = ? and hiredate > ? ";
		try(Scanner sc = new Scanner(System.in);
			Connection cn = openConnecion();
			PreparedStatement pst = cn.prepareStatement(sql);){
			System.out.println("Enter dept no and date");
			pst.setInt(1, sc.nextInt());
			pst.setDate(2, Date.valueOf(sc.next()));
			
			try(ResultSet rst = pst.executeQuery()){
				while(rst.next()) {
					System.out.println("Emp no " + rst.getInt(1) 
									+ " Name " + rst.getString(2) 
									+ " Hire Date " + rst.getDate(3) 
									+ " Job " + rst.getString(4) 
									+ " Dept no " + rst.getInt(5));
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

}
