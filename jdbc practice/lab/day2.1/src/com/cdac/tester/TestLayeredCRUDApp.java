package com.cdac.tester;

import java.sql.Date;
import java.util.Scanner;

import com.cdac.dao.UserDao;
import com.cdac.dao.UserDaoImpl;
import com.cdac.pojos.User;

public class TestLayeredCRUDApp {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			// init phase - create dao instance
			UserDao userDao = new UserDaoImpl();
			boolean exit = false;
			while (!exit) {
				// client servicing phase
				System.out.println("Options 1. Display all users 2. Sign In  3. Sign Up 0. Exit");
				try {
					switch (sc.nextInt()) {
					case 1:
						System.out.println("All users -");
						userDao.getAllUsers().forEach(System.out::println);// user -> System.out.println(user)
						break;
					case 2:
						System.out.println("Enter email n pwd for sign in");
						System.out.println(userDao.signIn(sc.next(), sc.next()));
						break;
					case 3:
						System.out.println("Enter firstName,  lastName,email ,pwd,   dob for sign up");
						// create instance of user (voter)
						User voter = new User(sc.next(), sc.next(), sc.next(), sc.next(), Date.valueOf(sc.next()));
						System.out.println(userDao.signUp(voter));
						break;

					case 0: // shut down (destroy)
						exit = true;
						userDao.cleanUp();
						break;
					}
				} catch (Exception e) {
					System.out.println(e);
					sc.nextLine();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
