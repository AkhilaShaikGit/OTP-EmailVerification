package com.as.emailer.service;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.as.emailer.model.UserResponse;
import com.as.emailer.model.Users;
import com.as.emailer.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final EmailService emailService;
	
	private final UserRepository userRepo;
	
	@Override
	public UserResponse addUser(Users user) {
	Users existingEmail =	userRepo.findByEmail(user.getEmail());
	if(existingEmail != null && existingEmail.isVerified()) {
		throw new RuntimeException("Email is already verified");
	}
	String otp = getOtp(user.getEmail());
	System.out.println("get otp "+otp);
		Users user1 = Users.builder()
				.username(user.getUsername())
				.email(user.getEmail())
				.password(user.getPassword())
				.otp(otp)
				.build();
		userRepo.save(user1);
		
		String subject = "Email Verification Code";
		String body = "You verification code is : " +otp +"\n do not share the otp code \n regards \n email verification service";
		emailService.send(user1.getEmail(), subject, body);
		return UserResponse.builder()
				.username(user1.getUsername())
				.email(user1.getEmail())
				.build();
	}
	
	private String getOtp(String email) {
		Random random = new Random();
		int otp = 1000 +random.nextInt(9999);
		return String.valueOf(otp);
	}
	
	public void isVerified(String email, String otp) {
		Users current = userRepo.findByEmail(email);
		if(current == null) {
			throw new RuntimeException("Not a valid email");
		}else if(current.isVerified()) {
			throw new RuntimeException("Email is already verified");
		}else if(otp.equals(current.getOtp())){
			current.setVerified(true);
			userRepo.save(current);
		}
	}

}
