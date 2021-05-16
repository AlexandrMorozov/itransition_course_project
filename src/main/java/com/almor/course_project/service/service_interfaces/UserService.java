package com.almor.course_project.service.service_interfaces;

import com.almor.course_project.dto.UserDto;
import com.almor.course_project.dto.UserDtoLite;
import com.almor.course_project.dto.requests.JwtResponse;
import com.almor.course_project.dto.requests.LoginRequest;
import com.almor.course_project.dto.requests.SigninRequest;
import com.almor.course_project.model.Bonus;
import com.almor.course_project.model.Campaign;
import com.almor.course_project.model.Role;

import java.util.List;


public interface UserService {

    void registerUser(SigninRequest user, Role newUserRole);

    JwtResponse authorizeUser(LoginRequest user);

    UserDto getUser(String userName);

    UserDtoLite getUserEssentials(String userName);

    List<UserDto> getAllUsers();

    boolean isUserExists(String userName);

    List<Campaign> deleteUser(UserDto userDto);

    void changeUserStatus(List<UserDto> usersDto, boolean status);

    void addRole(List<UserDto> usersDto, Role newRole);

    boolean changeUserName(String userName);

    boolean changeUserMail(String oldEmail, String newEmail);

    void addRating(Campaign ratedCampaign, int rating, Long userId);

    boolean purchaseBonus(Campaign targetCampaign, Long userId, Bonus bonus);
}
