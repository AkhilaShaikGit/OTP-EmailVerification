package com.as.emailer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.as.emailer.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
	
	public Users findByEmail(String email);

}
