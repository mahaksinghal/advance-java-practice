package com.voting.dao;

import static com.voting.utils.DBUtils.*;

import java.sql.*;

import com.voting.pojos.User;

public class UserDaoImpl implements UserDao {
	// state (non static fields)
	private Connection connection;
	private PreparedStatement pst1, pst2, pst3,pst4,pst5;

	// def ctor
	public UserDaoImpl() throws SQLException {
		// open cn
		connection = getConnection();
		// create pst1
		pst1 = connection.prepareStatement("select * from users where email=? and password=?");
		/*
		 * id | first_name | last_name | email | password | dob | status | role
		 */
		pst2 = connection.prepareStatement("insert into users values(default,?,?,?,?,?,?,?)");
		pst3 = connection.prepareStatement("update users set password=? where email=? and password=?");
		pst4=connection.prepareStatement("delete from users where id=?");
		pst5=connection.prepareStatement("update users set status=? where id=?");
		System.out.println("user dao created !");
	}

	@Override
	public User authenticateUser(String email, String pwd) throws SQLException {
		// 1. set IN params
		pst1.setString(1, email);
		pst1.setString(2, pwd);
		// 2. exec query
		try (ResultSet rst = pst1.executeQuery()) {
			// rst cursor - before the 1st row
			if (rst.next())
				/*
				 * int userId, String firstName, String lastName, String email, String password,
				 * Date dob, boolean status, String role
				 */
				return new User(rst.getInt(1), rst.getString(2), rst.getString(3), email, pwd, rst.getDate(6),
						rst.getBoolean(7), rst.getString(8));
		}
		return null;
	}

	@Override
	public String registerUser(User newUser) throws SQLException {
		// set IN params
		pst2.setString(1, newUser.getFirstName());
		pst2.setString(2, newUser.getLastName());
		pst2.setString(3, newUser.getEmail());
		pst2.setString(4, newUser.getPassword());
		pst2.setDate(5, newUser.getDob());
		pst2.setBoolean(6, false);
		pst2.setString(7, "voter");
		// exec query - exec update - DML
		int rowCount = pst2.executeUpdate();
		if (rowCount == 1)
			return "User rgeistered !!!";
		return "User registration failed !!!!!";
	}

	@Override
	public String changePassword(String email, String oldPass, String newPass) throws SQLException {
		// set IN params
		pst3.setString(1, newPass);
		pst3.setString(2, email);
		pst3.setString(3, oldPass);
		// exec query - exec update - DML
		int rowCount = pst3.executeUpdate();
		if (rowCount == 1)
			return "Password changed !!!";
		return "Changing password failed !!!!!";
	}

	
	@Override
	public String unsubscribeUser(int userId) throws SQLException {
		//set IN param - user id
		pst4.setInt(1, userId);
		int rowCount = pst4.executeUpdate();
		if (rowCount == 1)
			return "User un subscribed !";
		return "Un subscribing user failed !!!!!";
	}
	

	@Override
	public String updateVotingStatus(int voterId) throws SQLException {
		// set IN params
		pst5.setBoolean(1, true);
		pst5.setInt(2, voterId);
		int rowCount = pst5.executeUpdate();
		if (rowCount == 1)
			return "Updated voting status";
		return "Updation  failed !!!!!";
	}

	@Override
	public void cleanUp() throws SQLException {
		// close psts
		if (pst1 != null)
			pst1.close();
		if (pst2 != null)
			pst2.close();
		if (pst3 != null)
			pst3.close();
		if (pst4 != null)
			pst4.close();		
		System.out.println("user dao cleaned up !");

	}

}
