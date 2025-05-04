package com.voting.dao;

import java.sql.SQLException;
import java.util.List;

import com.voting.pojos.User;

public interface UserDao {
//add a method to get all users details
	List<User> getAllUsers() throws SQLException;
	//add a method for sign in
	User signIn(String email,String password) throws SQLException;
	//add a method for user sign up
	String signUp(User newVoter) throws SQLException;
	//add a method for updating voting status
	String updateVotingStatus(int voterId) throws SQLException;
	
	//add a method for clean up
	void cleanUp() throws SQLException;
}
