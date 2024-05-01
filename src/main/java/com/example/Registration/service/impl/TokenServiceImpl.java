package com.example.Registration.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Registration.entity.PasswordResetToken;
import com.example.Registration.entity.User;
import com.example.Registration.entity.VerificationToken;
import com.example.Registration.exception.InvalidTokenException;
import com.example.Registration.exception.TokenExpiredException;
import com.example.Registration.repository.PasswordResetTokenRepository;
import com.example.Registration.repository.UserRepository;
import com.example.Registration.repository.VerificationTokenRepository;
import com.example.Registration.service.EmailService;
import com.example.Registration.service.TokenService;

import java.time.LocalDateTime;
import java.util.UUID;
@Service
public class TokenServiceImpl implements TokenService{
	 @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private VerificationTokenRepository verificationTokenRepository;

	    @Autowired
	    private PasswordResetTokenRepository passwordResetTokenRepository;

	    @Autowired
	    private EmailService emailService;

	    @Override
	    public void registerUser(User user) {
	        userRepository.save(user);
	        generateVerificationToken(user);
	    }

	    @Override
	    public void generateVerificationToken(User user) {
	        String token = UUID.randomUUID().toString();
	        VerificationToken verificationToken = new VerificationToken();
	        verificationToken.setToken(token);
	        verificationToken.setUser(user);
	        verificationToken.setExpiryDate(LocalDateTime.now().plusHours(24)); // Set expiry time
	        verificationTokenRepository.save(verificationToken);
	        emailService.sendVerificationEmail(user.getEmail(), token); // Change here
	    }

	    @Override
	    public void generatePasswordResetToken(User user) {
	        String token = UUID.randomUUID().toString();
	        PasswordResetToken passwordResetToken = new PasswordResetToken();
	        passwordResetToken.setToken(token);
	        passwordResetToken.setUser(user);
	        passwordResetToken.setExpiryDate(LocalDateTime.now().plusHours(24)); // Set expiry time
	        passwordResetTokenRepository.save(passwordResetToken);
	        emailService.sendPasswordResetEmail(user.getEmail(), token); // Change here
	    }

	    @Override
	    public void verifyUser(String token) {
	        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
	        if (verificationToken != null && !verificationToken.isExpired()) {
	            User user = verificationToken.getUser();
	            user.setVerified(true);
	            userRepository.save(user);
	            verificationTokenRepository.delete(verificationToken);
	        } else if (verificationToken != null) {
	            // Token expired
	            verificationTokenRepository.delete(verificationToken);
	            throw new TokenExpiredException("Verification token expired");
	        } else {
	            // Token not found or invalid
	            throw new InvalidTokenException("Invalid verification token");
	        }
	    }

	    @Override
	    public void resetPassword(String token, String newPassword) {
	        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(token);
	        if (passwordResetToken != null && !passwordResetToken.isExpired()) {
	            User user = passwordResetToken.getUser();
	            user.setPassword(newPassword);
	            userRepository.save(user);
	            passwordResetTokenRepository.delete(passwordResetToken);
	        } else if (passwordResetToken != null) {
	            // Token expired
	            passwordResetTokenRepository.delete(passwordResetToken);
	            throw new TokenExpiredException("Password reset token expired");
	        } else {
	            // Token not found or invalid
	            throw new InvalidTokenException("Invalid password reset token");
	        }
	    }
}
