package com.voting.dao;

import java.sql.SQLException;

import com.voting.pojos.User;

public interface UserDao {
//sign in
	User authenticateUser(String email, String pwd) throws SQLException;
	//sign up
	String registerUser(User newUser) throws SQLException;
	//change pwd
	String changePassword(String email,String oldPass,String newPass) throws SQLException;
	//clean up
	void cleanUp() throws SQLException;
	String unsubscribeUser(int userId) throws SQLException;
	//add a method to change voting status
	String updateVotingStatus(int voterId) throws SQLException;
}
