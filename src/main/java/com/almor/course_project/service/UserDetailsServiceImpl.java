package com.almor.course_project.service;

import com.almor.course_project.dto.JwtResponse;
import com.almor.course_project.dto.requests.LoginRequest;
import com.almor.course_project.dto.requests.SigninRequest;
import com.almor.course_project.model.User;
import com.almor.course_project.repos.RoleRepo;
import com.almor.course_project.repos.UserRepo;
import com.almor.course_project.service.jwt.JwtUtils;
import com.almor.course_project.service.jwt.UserDetailsImpl;
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
public class UserDetailsServiceImpl implements UserDetailsService {

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
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByName(username);

        if (user == null) {
            throw new UsernameNotFoundException("");
        }

        return UserDetailsImpl.buildUserDetails(user);
    }

    public void addUser(/*UserDto*/SigninRequest user) {
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
    public JwtResponse checkUser(/*UserDto*/LoginRequest user) {

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
}
