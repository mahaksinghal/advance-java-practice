package com.cdac.tester;

import static com.cdac.utils.HibernateUtils.getFactory;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.cdac.dao.UserDao;
import com.cdac.dao.UserDaoImpl;
import com.cdac.entities.UserRole;

public class GetUsersByRoleAndDob {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in); 
				SessionFactory sf = getFactory()) {
			// create DAO instance
			UserDao dao = new UserDaoImpl();
			System.out.println("Enter user role n dob");
					//invoke dao's method
			dao.getUserDetailsByRoleAndDob
					(UserRole.valueOf(sc.next().toUpperCase()),
							LocalDate.parse(sc.next()))
					.forEach(System.out::println);
		} //sf => DBCP - closing
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
