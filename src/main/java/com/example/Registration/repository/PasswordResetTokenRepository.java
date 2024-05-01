package com.example.Registration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Registration.entity.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long>{
	PasswordResetToken findByToken(String token);
}
