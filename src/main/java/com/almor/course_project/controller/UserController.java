package com.almor.course_project.controller;

import com.almor.course_project.dto.ResultMessageDto;
import com.almor.course_project.dto.UserDto;
import com.almor.course_project.dto.UserDtoLite;
import com.almor.course_project.model.Role;
import com.almor.course_project.service.RoleService;
import com.almor.course_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getUser(String name) {
        if (userService.isUserExists(name)) {
            return ResponseEntity.ok(userService.getUser(name));
        }
        return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/essentials")
    @PreAuthorize("hasRole('USER')")
    public UserDtoLite getUserEssentials(String name) {
        return userService.getUserEssentials(name);
    }

    @GetMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUsers(/*String name*//*Long userId*/List<UserDto> users) {
        userService.deleteUsers(/*name*/users);
        return ResponseEntity.ok("");
    }

    @PostMapping("/changestatus")
    public ResponseEntity<?> changeUsersStatus(/*Long userId*/List<UserDto> users, boolean status) {
        userService.changeUserStatus(users, status);
        return ResponseEntity.ok("");
    }

    @PostMapping("/addrole")
    public ResponseEntity<?> addRoleToUsers(/*Long userId*/List<UserDto> users, String roleName) {

        Role newRole = roleService.getRole(roleName);

        userService.addRole(/*userId*/users, newRole);

        return ResponseEntity.ok("");
    }

    @GetMapping("/getallusers")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/changename")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> changeUserName(String name) {

        boolean result = userService.changeUserName(name);
        String message = result ? "1" : "2";

        return ResponseEntity.ok(new ResultMessageDto(result, message));
    }

    @GetMapping("/changemail")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> changeUserMail(String oldEmail, String newEmail) {
        boolean result = userService.changeUserMail(oldEmail, newEmail);
        String message = result ? "1" : "2";

        return ResponseEntity.ok(new ResultMessageDto(result, message));
    }

}
