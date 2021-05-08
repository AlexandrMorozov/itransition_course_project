package com.almor.course_project.controller;

import com.almor.course_project.dto.ResultMessageDto;
import com.almor.course_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/*@CrossOrigin(origins = "*", maxAge = 3600)*/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getUser(String name) {
        if (userService.isUserExists(name)) {
            return ResponseEntity.ok(userService.getUser(name));
        }
        return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(String name) {
        userService.deleteUser(name);
        return ResponseEntity.ok("");
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

    @GetMapping("/isexists")
    public ResponseEntity<?> isUserExists(String username) {

        //!!!
        Boolean n = new Boolean(userService.isUserExists(username));
        /*Boolean.userService.isUserExists(username);
        if (userService.isUserExists(username)) {
            return ResponseEntity.ok();
        }*/

        return ResponseEntity.ok(n);
    }
}