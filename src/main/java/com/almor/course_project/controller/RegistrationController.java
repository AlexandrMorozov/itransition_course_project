package com.almor.course_project.controller;

import com.almor.course_project.dto.MessageResponse;
import com.almor.course_project.dto.requests.LoginRequest;
import com.almor.course_project.dto.requests.SigninRequest;
import com.almor.course_project.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class RegistrationController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

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
