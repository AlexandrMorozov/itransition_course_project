package com.almor.course_project.controller;

import com.almor.course_project.dto.UserDto;
import com.almor.course_project.dto.UserDtoLite;
import com.almor.course_project.dto.mappings.BonusMapping;
import com.almor.course_project.dto.mappings.CampaignMapping;
import com.almor.course_project.dto.requests.*;
import com.almor.course_project.model.*;
import com.almor.course_project.service.service_interfaces.CampaignService;
import com.almor.course_project.service.service_interfaces.CloudService;
import com.almor.course_project.service.service_interfaces.RoleService;
import com.almor.course_project.service.service_interfaces.UserService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private CampaignService campaignService;

    @GetMapping
    public UserDto getUser(String name) {
        if (userService.isUserExists(name)) {
            return userService.getUser(name);
        }
        return null;
    }

    @GetMapping("/essentials")
    public UserDtoLite getUserEssentials(String name) {
        return userService.getUserEssentials(name);
    }

    @PostMapping("/delete")
    public ResponseEntity deleteUsers(@RequestBody List<UserDto> users) {

        for (UserDto user : users) {

            List<Campaign> deletedCampaigns = userService.deleteUser(user);

            for (Campaign campaign : deletedCampaigns) {

                List<Gallery> deletedPictures = new ArrayList<>();
                deletedPictures.addAll(campaign.getPictures());

                cloudService.deleteImages(deletedPictures);
            }
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping("/changestatus")
    public ResponseEntity changeUsersStatus(@RequestBody UserStatusChangeRequest request) {

        userService.changeUserStatus(request.getUsers(), request.isNewStatus());

        return ResponseEntity.ok().build();
    }

    @PostMapping("/addrole")
    public ResponseEntity addRoleToUsers(@RequestBody RoleAssignmentRequest request) {

        Role newRole = roleService.getRole(request.getRoleName());
        userService.addRole(request.getUsers(), newRole);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/getallusers")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/changename")
    public ResponseEntity changeUserName(String name) {
        userService.changeUserName(name);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/changemail")
    public ResponseEntity changeUserMail(String oldEmail, String newEmail) {
        userService.changeUserMail(oldEmail, newEmail);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/addrating")
    public ResponseEntity addUserRating(Long userId, String campaignName, int ratingValue) {

        Campaign campaign = campaignService.getCampaign(campaignName);
        userService.addRating(campaign, ratingValue, userId);

        return ResponseEntity.ok().build();

    }

    @PostMapping("/purchasebonus")
    public boolean purchaseBonus(@RequestBody BonusAddingRequest bonusRequest) {

        Bonus bonus = Mappers.getMapper(BonusMapping.class).dtoToEntity(bonusRequest.getBonus());
        Campaign campaign = Mappers.getMapper(CampaignMapping.class)
                .dtoToEntity(bonusRequest.getCampaign());

        Long userId = bonusRequest.getUserId();

        if (userService.purchaseBonus(campaign, userId, bonus)) {
            campaignService.receivePayment(campaign.getId(), bonus.getSum());
            return true;
        }

        return false;
    }

}
