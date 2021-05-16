package com.almor.course_project.controller;

import com.almor.course_project.dto.requests.JwtResponse;
import com.almor.course_project.dto.requests.LoginRequest;
import com.almor.course_project.dto.requests.SigninRequest;
import com.almor.course_project.model.Role;
import com.almor.course_project.service.entity_services.RoleService;
import com.almor.course_project.service.entity_services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @PostMapping("/registration")
    public boolean createUser(@RequestBody SigninRequest user) {

        if (userService.isUserExists(user.getUsername())) {

            Role newUserRole = roleService.getRole("ROLE_USER");
            userService.registerUser(user, newUserRole);

            return true;
        }

        return false;
    }

    @PostMapping("/authentication")
    public JwtResponse authorizeUser(@RequestBody LoginRequest user) {
        return userService.authorizeUser(user);
    }

}
