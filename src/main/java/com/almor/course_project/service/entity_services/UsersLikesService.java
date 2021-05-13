package com.almor.course_project.service.entity_services;

import com.almor.course_project.dto.CommentRateDto;
import com.almor.course_project.model.Comment;
import com.almor.course_project.model.User;
import com.almor.course_project.repos.UsersLikesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersLikesService {

    @Autowired
    private UsersLikesRepo usersLikesRepo;

    public void rateComment(CommentRateDto commentRateDto, User user, Comment comment) {

    }

}
