package com.example.myedu.service;

import com.example.myedu.entity.User;
import com.example.myedu.model.request.SignupRequest;
import com.example.myedu.model.response.MessageResponse;
import com.example.myedu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public ResponseEntity<?> createNewTeacher(SignupRequest signupRequest) {
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Username da ton tai"));
        }
        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setName(signupRequest.getName());
        user.setRoleId(2);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("Them Giao vien thanh cong"));
    }
}
