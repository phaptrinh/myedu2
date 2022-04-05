package com.example.myedu.controller;

import com.example.myedu.entity.Class;
import com.example.myedu.entity.User;
import com.example.myedu.model.UserDTO;
import com.example.myedu.model.response.MessageResponse;
import com.example.myedu.security.service.UserDetailsImpl;
import com.example.myedu.service.ClassService;
import com.example.myedu.service.EnrollmentService;
import com.example.myedu.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@PreAuthorize("hasAuthority('STUDENT')")
public class StudentController {
    @Autowired
    UserService userService;

    @Autowired
    ClassService classService;

    @Autowired
    EnrollmentService enrollmentService;

    @Autowired
    ModelMapper modelMapper;

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

    @GetMapping("/info")
    public ResponseEntity<?> getInfo(Authentication authentication) {
        Object principal = authentication.getPrincipal();

        if (principal instanceof UserDetailsImpl) {
            String username = ((UserDetailsImpl) principal).getUsername();
            User user = userService.getUserByUsername(username);
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
            return new ResponseEntity<>(userDTO, new HttpHeaders(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @PostMapping("/classes/{id}")
    public ResponseEntity<?> enrollClassById(Authentication authentication, @PathVariable("id") int classId) {
        Object principal = authentication.getPrincipal();

        if (principal instanceof UserDetailsImpl) {
            int studentUserId = ((UserDetailsImpl) principal).getId();
            if (enrollmentService.existsByStudentUserIdAndClassId(studentUserId, classId)) {
                return new ResponseEntity<>(new MessageResponse("Hoc sinh da tham gia lop nay"), new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
            }
            if (!enrollmentService.existsEnrollmentByClassId(classId)) {
                return new ResponseEntity<>(new MessageResponse("Khong ton tai lop hoc nay"), new HttpHeaders(), HttpStatus.NOT_FOUND);
            }
            enrollmentService.enrollClassById(studentUserId, classId);
            return new ResponseEntity<>(new MessageResponse("Tham gia lop thanh cong"), new HttpHeaders(), HttpStatus.OK);

        }
        return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

}
