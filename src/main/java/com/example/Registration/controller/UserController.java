package com.example.Registration.controller;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import com.example.Registration.dto.UserDto;
//import com.example.Registration.entity.User;
//import com.example.Registration.service.TokenService;
//import com.example.Registration.service.UserService;
//import com.example.Registration.service.impl.TokenServiceImpl;
//
//import ch.qos.logback.core.model.Model;
//import jakarta.validation.Valid;
//
//@Controller
//@RequestMapping("/api")
//public class UserController {
//        @Autowired
//        private UserService userService;
//
//        @Autowired
//        private TokenService tokenService;
//
//        @GetMapping("/index")
//        public String home() {
//            return "index";
//        }
//
//        // handler method to handle login request
//        @GetMapping("/login")
//        public String login() {
//            return "login";
//        }
//
//        // handler method to handle user registration form request
//        @GetMapping("/register")
//        public String showRegistrationForm(Model model) {
//            // create model object to store form data
//            UserDto user = new UserDto();
//            model.addAttribute("user", user);
//            return "register";
//        }
//
//        // handler method to handle user registration form submit request
//        @PostMapping("/register/save")
//        public String registration(@Valid @ModelAttribute("user") UserDto userDto,
//                                   BindingResult result,
//                                   Model model) {
//            User existingUser = userService.findUserByEmail(userDto.getEmail());
//
//            if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
//                result.rejectValue("email", null,
//                        "There is already an account registered with the same email");
//            }
//
//            if (result.hasErrors()) {
//                model.addAttribute("user", userDto);
//                return "/register";
//            }
//
//            userService.saveUser(userDto);
//
//            // Generate verification token and send email
//            User user = userService.findUserByEmail(userDto.getEmail());
//            tokenService.generateVerificationToken(user);
//
//            return "redirect:/register?success";
//        }
//
//        // handler method to handle list of users
//        @GetMapping("/users")
//        public String users(Model model) {
//            List<UserDto> users = userService.findAllUsers();
//            model.addAttribute("users", users);
//            return "users";
//        }
//
//        // handler method to generate verification token
//        @PostMapping("/generate-verification-token")
//        public ResponseEntity<String> generateVerificationToken(@RequestParam String email) {
//            User user = userService.findUserByEmail(email);
//            tokenService.generateVerificationToken(user);
//            return ResponseEntity.ok("Verification token generated and email sent.");
//        }
//
//        // handler method to handle forgot password
//        @PostMapping("/forgot-password")
//        public ResponseEntity<String> forgotPassword(@RequestParam String email) {
//            User user = userService.findUserByEmail(email);
//            tokenService.generatePasswordResetToken(user);
//            return ResponseEntity.ok("Password reset token generated and email sent.");
//        }
//
//        // handler method to handle email verification
//        @GetMapping("/verify-email")
//        public ResponseEntity<String> verifyEmail(@RequestParam String token) {
//            tokenService.verifyUser(token);
//            return ResponseEntity.ok("Email verified successfully.");
//        }
//
//        // handler method to handle password reset
//        @PostMapping("/reset-password")
//        public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
//            tokenService.resetPassword(token, newPassword);
//            return ResponseEntity.ok("Password reset successfully.");
//        }
//    @PostMapping("/register")
//    public ResponseEntity<String> registerUser(@RequestBody User user) {
//        tokenService.registerUser(user);
//        return ResponseEntity.ok("User registered successfully. Verification email sent.");
//    }
//
//    @PostMapping("/generate-verification-token")
//    public ResponseEntity<String> generateVerificationToken(@RequestParam String email) {
//        User user = new User(); // You might want to load user from database
//        tokenService.generateVerificationToken(user);
//        return ResponseEntity.ok("Verification token generated and email sent.");
//    }
//
//    @PostMapping("/forgot-password")
//    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
//        User user = new User(); // You might want to load user from database
//        tokenService.generatePasswordResetToken(user);
//        return ResponseEntity.ok("Password reset token generated and email sent.");
//    }
//
//    @GetMapping("/verify-email")
//    public ResponseEntity<String> verifyEmail(@RequestParam String token) {
//        tokenService.verifyUser(token);
//        return ResponseEntity.ok("Email verified successfully.");
//    }
//
//    @PostMapping("/reset-password")
//    public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
//        tokenService.resetPassword(token, newPassword);
//        return ResponseEntity.ok("Password reset successfully.");
//    }
//}


import com.example.Registration.dto.UserDto;
import com.example.Registration.entity.User;
import com.example.Registration.service.TokenService;
import com.example.Registration.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class UserController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserDto userDto, BindingResult result) {
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null, "There is already an account registered with the same email");
        }

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid user data");
        }

        userService.saveUser(userDto);

        // Generate verification token and send email
        User user = userService.findUserByEmail(userDto.getEmail());
        tokenService.generateVerificationToken(user);

        return ResponseEntity.ok("User registered successfully. Verification email sent.");
    }

    @PostMapping("/generate-verification-token")
    public ResponseEntity<String> generateVerificationToken(@RequestParam String email) {
        User user = userService.findUserByEmail(email);
        tokenService.generateVerificationToken(user);
        return ResponseEntity.ok("Verification token generated and email sent.");
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        User user = userService.findUserByEmail(email);
        tokenService.generatePasswordResetToken(user);
        return ResponseEntity.ok("Password reset token generated and email sent.");
    }

    @GetMapping("/verify-email")
    public ResponseEntity<String> verifyEmail(@RequestParam String token) {
        tokenService.verifyUser(token);
        return ResponseEntity.ok("Email verified successfully.");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
        tokenService.resetPassword(token, newPassword);
        return ResponseEntity.ok("Password reset successfully.");
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }
}


