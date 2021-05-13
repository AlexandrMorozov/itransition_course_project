package com.almor.course_project.service.entity_services;

import com.almor.course_project.dto.JwtResponse;
import com.almor.course_project.dto.UserDto;
import com.almor.course_project.dto.UserDtoLite;
import com.almor.course_project.dto.mappings.UserMapping;
import com.almor.course_project.dto.requests.LoginRequest;
import com.almor.course_project.dto.requests.SigninRequest;
import com.almor.course_project.model.Campaign;
import com.almor.course_project.model.Role;
import com.almor.course_project.model.User;
import com.almor.course_project.repos.RoleRepo;
import com.almor.course_project.repos.UserRepo;
import com.almor.course_project.service.jwt.JwtUtils;
import com.almor.course_project.service.jwt.UserDetailsImpl;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    //Explain-Explain!!!
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepo.findByName(username);

        if (!user.isPresent()) {
            throw new UsernameNotFoundException("Not found!");
        }

        return UserDetailsImpl.buildUserDetails(user.get());
    }

    public void addUser(SigninRequest user) {

        //Refactor
        User newUser = new User();
        newUser.setName(user.getUsername());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setEmail(user.getEmail());
        newUser.setEnabled(true);
        //Think of role assignment mechanism
        newUser.setRoles(Collections.singleton(roleRepo.findByRoleName("ROLE_USER")));

        userRepo.save(newUser);
    }

    //Refactor
    public JwtResponse checkUser(LoginRequest user) {

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwt = jwtUtils.generateJwtToken(auth);

        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(),
                userDetails.getEmail(), roles);
    }


    public UserDto getUser(String userName) {
        User user = userRepo.findByName(userName).get();
        return Mappers.getMapper(UserMapping.class).entityToDto(user);
    }

    public UserDtoLite getUserEssentials(String userName) {
        User user = userRepo.findByName(userName).get();
        return Mappers.getMapper(UserMapping.class).EntityToDtoLite(user);
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepo.findAll();

        return Mappers.getMapper(UserMapping.class).fromListModelToListDto(users);
    }

    public boolean isUserExists(String userName) {

        if(userRepo.findByName(userName).isPresent()) {
            return true;
        }
        return false;
    }

    private void terminateCurrentUserSession() {
        SecurityContextHolder.getContext()
                .getAuthentication().setAuthenticated(false);
    }



    public List<Campaign> deleteUser(UserDto userDto) {

        User user = Mappers.getMapper(UserMapping.class).dtoToEntity(userDto);

        List<Campaign> deletedCampaigns = new ArrayList<>();

        deletedCampaigns.addAll(user.getCampaigns());

        /*if (user.) {

        }*/

        userRepo.delete(user);

        return deletedCampaigns;
    }


    public void changeUserStatus(List<UserDto> usersDto, boolean status) {

        List<User> users = Mappers.getMapper(UserMapping.class)
                .fromListDtoToListModel(usersDto);

        for(int i = 0; i < users.size(); i++) {

            users.get(i).setEnabled(status);

          /*  if (users.get(i).) {

            }*/
        }

        userRepo.saveAll(users);

    }

    public void addRole(List<UserDto> usersDto, Role newRole) {

        List<User> users = Mappers.getMapper(UserMapping.class)
                .fromListDtoToListModel(usersDto);

        for (int i = 0; i < users.size(); i++) {

            if (!users.get(i).isOwnsRole(newRole)) {

                users.get(i).addRole(newRole);

                userRepo.save(users.get(i));
            }
        }
    }

    //Refactor
    public boolean changeUserName(String userName) {
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();

        if (userRepo.findByName(userName) == null) {
            User user = userRepo.findByName(currentUserName).get();
            user.setName(userName);
            userRepo.save(user);
            return true;
        }

        return false;
    }

    //Refactor
    public boolean changeUserMail(String oldEmail, String newEmail) {

        if (userRepo.findByEmail(newEmail) == null) {
            User user = userRepo.findByEmail(oldEmail).get();
            user.setEmail(newEmail);
            userRepo.save(user);
            return true;
        }
        return false;
    }

}
