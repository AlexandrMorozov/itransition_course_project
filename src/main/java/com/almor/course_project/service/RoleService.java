package com.almor.course_project.service;

import com.almor.course_project.model.Role;
import com.almor.course_project.repos.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepo roleRepo;

    public Role getRole(String roleName) {
        return roleRepo.findByRoleName(roleName);
    }

}
