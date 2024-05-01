package com.example.Registration.service;


import com.example.Registration.dto.UserDto;
import com.example.Registration.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}
