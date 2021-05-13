package com.almor.course_project.controller;

import com.almor.course_project.dto.UserDto;
import com.almor.course_project.dto.UserDtoLite;
import com.almor.course_project.dto.requests.RoleAssignmentRequest;
import com.almor.course_project.dto.requests.UserStatusChangeRequest;
import com.almor.course_project.model.Campaign;
import com.almor.course_project.model.Gallery;
import com.almor.course_project.model.Role;
import com.almor.course_project.service.cloud_service.CloudService;
import com.almor.course_project.service.entity_services.RoleService;
import com.almor.course_project.service.entity_services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private CloudService cloudService;

    @GetMapping
    public ResponseEntity<?> getUser(String name) {
        if (userService.isUserExists(name)) {
            return ResponseEntity.ok(userService.getUser(name));
        }
        return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/essentials")
    public UserDtoLite getUserEssentials(String name) {
        return userService.getUserEssentials(name);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteUsers(@RequestBody List<UserDto> users) {

        for (int i = 0; i < users.size(); i++) {

            List<Campaign> deletedCampaigns = userService.deleteUser(users.get(i));

            for (int j = 0; j < deletedCampaigns.size(); j++) {

                List<Gallery> deletedPictures = new ArrayList<>();
                deletedPictures.addAll(deletedCampaigns.get(j).getPictures());

                cloudService.deleteImages(deletedPictures);
            }
        }

        return ResponseEntity.ok("");
    }

    @PostMapping("/changestatus")
    public ResponseEntity<?> changeUsersStatus(@RequestBody UserStatusChangeRequest request) {

        String d = "";

        userService.changeUserStatus(request.getUsers(), request.isNewStatus());

        return ResponseEntity.ok("");
    }

    @PostMapping("/addrole")
    public ResponseEntity<?> addRoleToUsers(@RequestBody RoleAssignmentRequest request) {

        Role newRole = roleService.getRole(request.getRoleName());

        userService.addRole(request.getUsers(), newRole);

        return ResponseEntity.ok("");
    }

    @GetMapping("/getallusers")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/changename")
    public ResponseEntity<?> changeUserName(String name) {

        //refactor
        boolean result = userService.changeUserName(name);
        //String message = result ? "1" : "2";

        return ResponseEntity.ok(/*new ResultMessageDto(result, message)*/"");
    }

    @GetMapping("/changemail")
    public ResponseEntity<?> changeUserMail(String oldEmail, String newEmail) {
        //refactor
        boolean result = userService.changeUserMail(oldEmail, newEmail);
        //String message = result ? "1" : "2";

        return ResponseEntity.ok(/*new ResultMessageDto(result, message)*/"");
    }

}
