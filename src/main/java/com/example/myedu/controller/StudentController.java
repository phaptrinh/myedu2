package com.example.myedu.controller;

import com.example.myedu.entity.Class;
import com.example.myedu.security.service.UserDetailsImpl;
import com.example.myedu.service.ClassService;
import com.example.myedu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
//@PreAuthorize("hasRole('ROLE_STUDENT')")
@PreAuthorize("hasAuthority('STUDENT')")
public class StudentController {
    @Autowired
    UserService userService;

    @Autowired
    ClassService classService;

    @GetMapping("/myClasses")
    public ResponseEntity<List<Class>> getMyClasses(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        System.out.println(authentication.getAuthorities());

        if (principal instanceof UserDetailsImpl) {
            int id = ((UserDetailsImpl)principal).getId();
            List<Class> classes = classService.getStudentClassesById(id);
            return new ResponseEntity<>(classes, new HttpHeaders(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);

    }
}
