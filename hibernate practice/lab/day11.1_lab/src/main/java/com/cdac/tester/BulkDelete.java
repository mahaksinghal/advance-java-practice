package com.cdac.tester;

import static com.cdac.utils.HibernateUtils.getFactory;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.cdac.dao.UserDao;
import com.cdac.dao.UserDaoImpl;

public class BulkDelete {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in); 
				SessionFactory sf = getFactory()) {
			
			// create DAO instance
			UserDao dao = new UserDaoImpl();
			System.out.println("Enter Dob , to delete users born before date");
			// invoke dao's method
			System.out.println(dao.deleteUsers(LocalDate.parse(sc.next())));

		} // sf => DBCP - closing
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
