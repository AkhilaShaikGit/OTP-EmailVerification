package com.as.emailer.service;

import com.as.emailer.model.UserResponse;
import com.as.emailer.model.Users;

public interface UserService {
	
	UserResponse addUser(Users user);
	
	public void isVerified(String email, String otp);

}
