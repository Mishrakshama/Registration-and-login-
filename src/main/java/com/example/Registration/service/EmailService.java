package com.example.Registration.service;
//	@Autowired
//    private JavaMailSender javaMailSender;
//
//    public void sendEmail(String to, String subject, String body) {
//        MimeMessage message = javaMailSender.createMimeMessage();
//        try {
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//            helper.setTo(to);
//            helper.setSubject(subject);
//            helper.setText(body, true);
//            javaMailSender.send(message);
//        } catch (MessagingException e) {
//            // Log the error
//            System.err.println("Failed to send email: " + e.getMessage());
//            // You can also throw a custom exception or handle it based on your application logic
//            // throw new EmailSendingException("Failed to send email", e);
//        }
//    }
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.mail.javamail.JavaMailSender;
	import org.springframework.mail.javamail.MimeMessageHelper;
	import org.springframework.stereotype.Service;

//	import javax.mail.MessagingException;
//	import javax.mail.internet.MimeMessage;
	import jakarta.mail.MessagingException;
	import jakarta.mail.internet.MimeMessage;

	@Service
	public class EmailService{

	    @Autowired
	    private JavaMailSender javaMailSender;

	    public void sendVerificationEmail(String to, String token) {
	        String subject = "Email Verification";
	        String body = "Click the link below to verify your email: http://yourdomain.com/verify-email?token=" + token;
	        sendEmail(to, subject, body);
	    }

	    public void sendPasswordResetEmail(String to, String token) {
	        String subject = "Password Reset";
	        String body = "Click the link below to reset your password: http://yourdomain.com/reset-password?token=" + token;
	        sendEmail(to, subject, body);
	    }

	    public void sendEmail(String to, String subject, String body) {
	        MimeMessage message = javaMailSender.createMimeMessage();
	        try {
	            MimeMessageHelper helper = new MimeMessageHelper(message, true);
	            helper.setTo(to);
	            helper.setSubject(subject);
	            helper.setText(body, true);
	            javaMailSender.send(message);
	        } catch (MessagingException e) {
	            e.printStackTrace();
	            // Handle exception
	        }
	    }
	}

