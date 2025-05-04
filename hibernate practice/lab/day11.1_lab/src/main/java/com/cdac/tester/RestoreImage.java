package com.cdac.tester;

import static com.cdac.utils.HibernateUtils.getFactory;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.cdac.dao.UserDao;
import com.cdac.dao.UserDaoImpl;
import com.cdac.entities.UserRole;

public class RestoreImage {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in); 
				SessionFactory sf = getFactory()) {
			// create DAO instance
			UserDao dao = new UserDaoImpl();
			System.out.println("Enter user email");
			String email=sc.nextLine();
			System.out.println("Enter image file name to store img from DB -> File");
			
			// invoke dao's method
			System.out.println(dao.restoreImage(email,sc.nextLine()));

		} // sf => DBCP - closing
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
