package com.example.myedu.controller;

import com.example.myedu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    UserService userService;

    @GetMapping("/myClasses")
    public ResponseEntity<List<Class>> getMyClasses() {
        return null;
    }
}
