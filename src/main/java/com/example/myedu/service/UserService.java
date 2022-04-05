package com.example.myedu.service;

import com.example.myedu.entity.User;

import java.util.List;
import java.util.Optional;


public interface UserService {
    List<User> getAllUsers();

    User getUserByUsername(String username);

}
