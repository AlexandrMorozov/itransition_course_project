package com.almor.course_project.service.entity_services;

import com.almor.course_project.model.Role;
import com.almor.course_project.repos.RoleRepo;
import com.almor.course_project.service.service_interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepo roleRepo;

    public Role getRole(String roleName) {
        return roleRepo.findByRoleName(roleName);
    }

}
