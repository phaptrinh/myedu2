package com.example.myedu.controller;

import com.example.myedu.entity.*;
import com.example.myedu.entity.Class;
import com.example.myedu.model.UserDTO;
import com.example.myedu.model.response.MessageResponse;
import com.example.myedu.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    ClassService classService;

    @Autowired
    SubjectService subjectService;

    @Autowired
    RoomService roomService;

    @Autowired
    TimeService timeService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/teachers")
    public ResponseEntity<?> getAllTeachers() {
        List<UserDTO> userDTOS = userService.getAllTeachers().stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
        if (userDTOS.isEmpty()) {
            return new  ResponseEntity<>(new MessageResponse("Khong tim thay gia tri"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userDTOS, new HttpHeaders(), HttpStatus.OK);
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

    @GetMapping("/subjects")
    public ResponseEntity<List<Subject>> getAllSubjects() {
        List<Subject> subjects = subjectService.getAllSubjects();
        return new ResponseEntity<>(subjects, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/rooms")
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        return new ResponseEntity<>(rooms, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/time")
    public ResponseEntity<List<Time>> getAllTime() {
        List<Time> time = timeService.getAllTime();
        return new ResponseEntity<>(time, new HttpHeaders(), HttpStatus.OK);
    }



}
