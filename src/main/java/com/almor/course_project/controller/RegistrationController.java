package com.almor.course_project.controller;

import com.almor.course_project.dto.MessageResponse;
import com.almor.course_project.dto.requests.LoginRequest;
import com.almor.course_project.dto.requests.SigninRequest;
import com.almor.course_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class RegistrationController {

    @Autowired
    private UserService userDetailsService;

    @PostMapping("/registration")
    public ResponseEntity<?> createUser(@RequestBody SigninRequest user) {
        userDetailsService.addUser(user);
        return ResponseEntity.ok(new MessageResponse(""));
    }

    @PostMapping("/authentication")
    public ResponseEntity<?> authorizeUser(@RequestBody LoginRequest user) {
        return ResponseEntity.ok(userDetailsService.checkUser(user));
    }

}
