package com.voting.dao;

import static com.voting.utils.DBUtils.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.voting.pojos.User;

public class UserDaoImpl implements UserDao {
	// state (fields)
	private Connection connection;
	private PreparedStatement pst1, pst2, pst3;

	// def ctor - will be invoked by above layer
	public UserDaoImpl() throws SQLException {
		// open cn
		connection = getConnection();
		// create pst1 - to get all users
		pst1 = connection.prepareStatement("select * from users");
		// create pst2- sign in user
		pst2 = connection.prepareStatement("select * from users where email=? and password=?");
		// create pst3- sign up voter
		pst3 = connection.prepareStatement("insert into users values(default,?,?,?,?,?,?,?)");

		System.out.println("user dao created !");
	}

	@Override
	public List<User> getAllUsers() throws SQLException {
		// create empty List - ArrayList
		List<User> users = new ArrayList<>();
		// execute query , get rst , process the same , perform ORM (row -> POJO)
		try (ResultSet rst = pst1.executeQuery()) {
			/*
			 * int userId, String firstName, String lastName, String email, String password,
			 * Date dob, boolean status, String userRole
			 */
			while (rst.next())
				users.add(new User(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getString(4),
						rst.getString(5), rst.getDate(6), rst.getBoolean(7), rst.getString(8)));
		}
		return users;
	}

	@Override
	public User signIn(String email, String password) throws SQLException {
		// set IN params - email , pwd
		pst2.setString(1, email);
		pst2.setString(2, password);
		// execute query - RST - ORM : User | null
		try (ResultSet rst = pst2.executeQuery()) {
			if (rst.next())
				return new User(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5),
						rst.getDate(6), rst.getBoolean(7), rst.getString(8));
		}
		return null;
	}

	@Override
	public String signUp(User newVoter) throws SQLException {

		// set IN params
		pst3.setString(1, newVoter.getFirstName());
		pst3.setString(2, newVoter.getLastName());
		pst3.setString(3, newVoter.getEmail());
		pst3.setString(4, newVoter.getPassword());
		pst3.setDate(5, newVoter.getDob());
		pst3.setBoolean(6, newVoter.isStatus());
		pst3.setString(7, newVoter.getUserRole());
		// exec query n get update count
		int updateCount = pst3.executeUpdate();
		if (updateCount == 1)
			return "Voter registered successfully !";

		return "Voter registration  failed!!!!";
		// Instead of returning err mesg , you can throw custom exception.
	}

	@Override
	public void cleanUp() throws SQLException {
		if (pst1 != null)
			pst1.close();
		if (pst2 != null)
			pst2.close();
		if (pst3 != null)
			pst3.close();
		
		System.out.println("user dao cleaned up !");

	}

}
