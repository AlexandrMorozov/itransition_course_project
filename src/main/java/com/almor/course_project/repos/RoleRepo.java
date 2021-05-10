package com.almor.course_project.repos;

import com.almor.course_project.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepo extends CrudRepository<Role, Long> {

    Role findByRoleName(String role);

}
