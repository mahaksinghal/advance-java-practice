package com.cdac.tester;

import static com.cdac.utils.HibernateUtils.getFactory;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.cdac.dao.UserDao;
import com.cdac.dao.UserDaoImpl;
import com.cdac.entities.UserRole;

public class SaveImage {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in); 
				SessionFactory sf = getFactory()) {
			// create DAO instance
			UserDao dao = new UserDaoImpl();
			System.out.println("Enter user id");
			Long userId=sc.nextLong();
			sc.nextLine();
			System.out.println("Enter image file name along with path");
			
			// invoke dao's method
			System.out.println(dao.saveImage(userId,sc.nextLine()));

		} // sf => DBCP - closing
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
