package com.almor.course_project.repos;

import com.almor.course_project.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends CrudRepository<User, Long> {

    Optional<User> findByName(String name);

    Optional<User> findByEmail(String email);

    List<User> findAll();

}
