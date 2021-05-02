package com.almor.course_project.repos;

import com.almor.course_project.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
    User findByName(String name);
    User findByEmail(String email);
}
