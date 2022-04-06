package com.example.myedu.controller;

import com.example.myedu.entity.Role;
import com.example.myedu.entity.User;
import com.example.myedu.model.UserDTO;
import com.example.myedu.model.request.SignupRequest;
import com.example.myedu.model.response.MessageResponse;
import com.example.myedu.security.service.UserDetailsImpl;
import com.example.myedu.service.RoleService;
import com.example.myedu.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class AdminController {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @GetMapping("/users")
    ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers().stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(users, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/roles")
    ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        return new ResponseEntity<>(roles, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/newTeacher")
    ResponseEntity<?> createNewTeacher(@Valid @RequestBody SignupRequest signupRequest, Errors errors) {
        if (errors.hasErrors()) {
            MessageResponse messageResponse = new MessageResponse(String.format("Cac truong %s chua dung dinh dang", errors.getFieldErrors().toString()));
            return new ResponseEntity<>(messageResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        return userService.createNewTeacher(signupRequest);
    }


}
