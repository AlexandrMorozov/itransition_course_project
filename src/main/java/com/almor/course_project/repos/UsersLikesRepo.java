package com.almor.course_project.repos;

import com.almor.course_project.model.composite_tables.UsersLikes;
import org.springframework.data.repository.CrudRepository;

public interface UsersLikesRepo extends CrudRepository<UsersLikes, Long> {
}
