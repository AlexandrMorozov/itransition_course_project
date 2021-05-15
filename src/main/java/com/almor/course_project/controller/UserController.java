package com.almor.course_project.controller;

import com.almor.course_project.dto.UserDto;
import com.almor.course_project.dto.UserDtoLite;
import com.almor.course_project.dto.mappings.BonusMapping;
import com.almor.course_project.dto.mappings.CampaignMapping;
import com.almor.course_project.dto.requests.BonusAddingRequest;
import com.almor.course_project.dto.requests.RoleAssignmentRequest;
import com.almor.course_project.dto.requests.UserStatusChangeRequest;
import com.almor.course_project.model.Bonus;
import com.almor.course_project.model.Campaign;
import com.almor.course_project.model.Gallery;
import com.almor.course_project.model.Role;
import com.almor.course_project.service.cloud_service.CloudService;
import com.almor.course_project.service.entity_services.CampaignService;
import com.almor.course_project.service.entity_services.RoleService;
import com.almor.course_project.service.entity_services.UserService;
import org.mapstruct.factory.Mappers;
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
    @Autowired
    private CampaignService campaignService;

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

        for (UserDto user : users) {

            List<Campaign> deletedCampaigns = userService.deleteUser(user);

            for (Campaign campaign : deletedCampaigns) {

                List<Gallery> deletedPictures = new ArrayList<>();
                deletedPictures.addAll(campaign.getPictures());

                cloudService.deleteImages(deletedPictures);
            }
        }

        return ResponseEntity.ok("");
    }

    @PostMapping("/changestatus")
    public ResponseEntity<?> changeUsersStatus(@RequestBody UserStatusChangeRequest request) {

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
        boolean result = userService.changeUserName(name);
        return ResponseEntity.ok("");
    }

    @GetMapping("/changemail")
    public ResponseEntity<?> changeUserMail(String oldEmail, String newEmail) {
        boolean result = userService.changeUserMail(oldEmail, newEmail);
        return ResponseEntity.ok("");
    }

    @GetMapping("/addrating")
    public ResponseEntity<?> addUserRating(Long userId, String campaignName, int ratingValue) {

        Campaign campaign = campaignService.getCampaign(campaignName);
        userService.addRating(campaign, ratingValue, userId);

        return ResponseEntity.ok("Rating added");

    }

    @PostMapping("/purchasebonus")
    public ResponseEntity<?> purchaseBonus(@RequestBody BonusAddingRequest bonusRequest) {

        Bonus bonus = Mappers.getMapper(BonusMapping.class).dtoToEntity(bonusRequest.getBonus());
        Campaign campaign = Mappers.getMapper(CampaignMapping.class).dtoToEntity(bonusRequest.getCampaign());
        Long userId = bonusRequest.getUserId();

        campaignService.receivePayment(campaign.getId(), bonus.getSum());
        userService.purchaseBonus(campaign, userId, bonus);

        return ResponseEntity.ok("");
    }



}
