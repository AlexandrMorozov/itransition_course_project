package com.almor.course_project.repos;

import com.almor.course_project.model.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepo extends CrudRepository<Comment, Long> {

    List<Comment> findAllById(Long campaignId);

}
