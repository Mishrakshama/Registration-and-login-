package com.example.Registration.repository;

import com.example.Registration.entity.User;
import com.example.Registration.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long>  {
	 VerificationToken findByToken(String token);
}
