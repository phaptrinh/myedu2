package com.example.myedu.service;

import com.example.myedu.entity.User;
import com.example.myedu.model.request.SignupRequest;
import com.example.myedu.model.response.MessageResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface UserService {
    List<User> getAllUsers();

    User getUserByUsername(String username);

    ResponseEntity<?> createNewTeacher(SignupRequest signupRequest);
}
