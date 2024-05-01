package com.example.Registration.service;

import com.example.Registration.entity.User;

public interface TokenService {
	 void registerUser(User user);
	    void generateVerificationToken(User user);
	    void generatePasswordResetToken(User user);
	    void verifyUser(String token);
	    void resetPassword(String token, String newPassword);
}
