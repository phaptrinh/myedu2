package com.example.myedu.controller;

import com.example.myedu.entity.User;
import com.example.myedu.entity.Class;
import com.example.myedu.service.ClassService;
import com.example.myedu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    ClassService classService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/class/{id}")
    public ResponseEntity<Class> getClassById(@PathVariable int id) {
        Class c = classService.getClassById(id);
        return new ResponseEntity<>(c, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/classes")
    public ResponseEntity<List<Class>> getAllClasses() {
        List<Class> classes = classService.getAllClasses();
        return new ResponseEntity<>(classes, new HttpHeaders(), HttpStatus.OK);
    }
}
