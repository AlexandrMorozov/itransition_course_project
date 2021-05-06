package com.almor.course_project.repos;

import com.almor.course_project.model.Tag;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TagRepo extends CrudRepository<Tag, Long> {

    List<Tag> findAll();
}
