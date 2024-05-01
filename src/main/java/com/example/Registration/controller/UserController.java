//package com.example.Registration.controller;
//
//import com.example.Registration.entity.User;
//import com.example.Registration.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @PostMapping("/register")
//    public ResponseEntity<?> registerUser(@RequestBody User user) {
//        return userService.saveUser(user);
//    }
//
//    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
//    public ResponseEntity<?> confirmUserAccount(@RequestParam("token")String confirmationToken) {
//        return userService.confirmEmail(confirmationToken);
//    }
//
//}
