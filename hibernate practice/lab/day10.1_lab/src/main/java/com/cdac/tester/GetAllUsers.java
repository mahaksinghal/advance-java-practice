package com.cdac.tester;

import static com.cdac.utils.HibernateUtils.getFactory;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.cdac.dao.UserDao;
import com.cdac.dao.UserDaoImpl;
import com.cdac.entities.User;
import com.cdac.entities.UserRole;

public class GetAllUsers {

	public static void main(String[] args) {
		try (SessionFactory sf = getFactory()) {
			// create DAO instance
			UserDao dao = new UserDaoImpl();
			
					//invoke dao's method
			dao.getAllUsersDetails().forEach(System.out::println);
		} //sf => DBCP - closing
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
