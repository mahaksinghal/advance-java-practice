package com.cdac.dao;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.cdac.entities.User;
import com.cdac.entities.UserRole;

public interface UserDao {
//register new user - signup
	String signUp(User transientUser);

	// get user details by its id
	User getUserDetailsById(Long userId);

	List<User> getAllUsersDetails();

	List<User> getUserDetailsByRoleAndDob(UserRole role, LocalDate date);

	List<User> getUserDetailsByRole(UserRole role);

	String changePassword(String email, String pwd, String newPwd);

	String deleteUserDetailsById(Long nextLong);

	String saveImage(Long userId, String imageFileName) throws IOException;

	String restoreImage(String email, String imageFileName) throws IOException;

	String deleteUsers(LocalDate dob);
}
