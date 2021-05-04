package com.almor.course_project.service;

import com.almor.course_project.dto.JwtResponse;
import com.almor.course_project.dto.UserDto;
import com.almor.course_project.dto.mappings.UserMapping;
import com.almor.course_project.dto.requests.LoginRequest;
import com.almor.course_project.dto.requests.SigninRequest;
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
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    //?????????
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

        User user = userRepo.findByName(username);

        if (user == null) {
            throw new UsernameNotFoundException("");
        }

        return UserDetailsImpl.buildUserDetails(user);
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

        return new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);
    }

    /*///////////////////////////////////////////*/

    public UserDto getUser(String userName) {

        User user = userRepo.findByName(userName);

        Mappers.getMapper(UserMapping.class).entityToDto(user);

        //Refactor
       /* UserDto rUser = new UserDto();
        rUser.setId(user.getId());
        rUser.setName(user.getName());
        rUser.setEmail(user.getEmail());
        rUser.setPassword(user.getPassword());
        rUser.setBonuses(user.getBonuses());
        rUser.setRoles(user.getRoles());*/

        return /*rUser;*/Mappers.getMapper(UserMapping.class).entityToDto(user);
    }

    public void deleteUser(String userName) {
        User user = userRepo.findByName(userName);
        userRepo.delete(user);
    }

    //Refactor
    public boolean changeUserName(String userName) {
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();

        if (userRepo.findByName(userName) == null) {
            User user = userRepo.findByName(currentUserName);
            user.setName(userName);
            userRepo.save(user);
            return true;
        }

        return false;
    }

    //Refactor
    public boolean changeUserMail(String oldEmail, String newEmail) {

        if (userRepo.findByEmail(newEmail) == null) {
            User user = userRepo.findByEmail(oldEmail);
            user.setEmail(newEmail);
            userRepo.save(user);
            return true;
        }

        return false;
    }

}
