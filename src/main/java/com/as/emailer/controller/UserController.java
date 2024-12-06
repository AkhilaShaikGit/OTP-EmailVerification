package com.as.emailer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.as.emailer.model.UserResponse;
import com.as.emailer.model.Users;
import com.as.emailer.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/api")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService service;
	
	@PostMapping("/register")
	public ResponseEntity<UserResponse> addUser(@RequestBody Users user) {
		 UserResponse users = service.addUser(user);
		if(users != null ) {
			return new ResponseEntity(users, HttpStatus.CREATED);
		}else {
			return new ResponseEntity(users, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/verification")
	public String emailVerification(@RequestParam String email,@RequestParam String otp) {
		service.isVerified(email, otp);
		return "Email verification is successful";
	}
	
	

}
