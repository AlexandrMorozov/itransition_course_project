package com.almor.course_project.repos;

import com.almor.course_project.model.Topic;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TopicRepo extends CrudRepository<Topic, Long> {

    List<Topic> findAll();
}
