package com.example.myedu.controller;

import com.example.myedu.entity.Class;
import com.example.myedu.entity.Subject;
import com.example.myedu.model.UserDTO;
import com.example.myedu.security.service.UserDetailsImpl;
import com.example.myedu.service.ClassService;
import com.example.myedu.service.SubjectService;
import com.example.myedu.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teacher")
@PreAuthorize("hasAuthority('TEACHER')")
public class TeacherController {
    @Autowired
    UserService userService;

    @Autowired
    ClassService classService;

    @Autowired
    SubjectService subjectService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/info")
    public ResponseEntity<UserDTO> getInfo(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        String username = ((UserDetailsImpl) principal).getUsername();
        UserDTO userDTO = modelMapper.map(userService.getUserByUsername(username), UserDTO.class);
        return new ResponseEntity<>(userDTO, new HttpHeaders(), HttpStatus.OK);
    }


    @GetMapping("/myClasses")
    public ResponseEntity<List<Class>> getMyClasses(Authentication authentication) {
        Object principal = authentication.getPrincipal();

        if (principal instanceof UserDetailsImpl) {
            int id = ((UserDetailsImpl)principal).getId();
            List<Class> classes = classService.getTeacherClassesById(id);
            return new ResponseEntity<>(classes, new HttpHeaders(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);

    }

    @GetMapping("/mySubjects")
    public ResponseEntity<?> getMySubjects(Authentication authentication) {
        Integer teacherId = ((UserDetailsImpl) authentication.getPrincipal()).getId();
        List<Integer> integers = classService.getSubjectIdsByTeacherId(teacherId);
        List<Subject> subjects = subjectService.getAllBySubjectId(integers);
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }
}
