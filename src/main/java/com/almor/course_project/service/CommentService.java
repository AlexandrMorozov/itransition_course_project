package com.almor.course_project.service;

import com.almor.course_project.dto.CommentDto;
import com.almor.course_project.dto.CommentRateDto;
import com.almor.course_project.dto.UserDto;
import com.almor.course_project.dto.mappings.CommentMapping;
import com.almor.course_project.model.Comment;
import com.almor.course_project.model.User;
import com.almor.course_project.model.composite_tables.UsersLikes;
import com.almor.course_project.model.composite_tables_keys.UsersLikesKey;
import com.almor.course_project.repos.CommentRepo;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepo commentRepo;

    public void addComment(CommentDto newComment, UserDto user) {

        Comment comment = Mappers.getMapper(CommentMapping.class).dtoToEntity(newComment);
        commentRepo.save(comment);

        /*Comment comment = new Comment();
        comment.setText(newComment.getComment());
        comment.setCommentAuthor(user);*/
    }

    /*public void rateComment(Long commentId, Long userId, boolean rate) {

        UsersLikesKey key = new UsersLikesKey(userId, commentId);
        UsersLikes rated = new UsersLikes(key, ,rate);
    }*/

    /*public List<Comment> receiveCampaignComments(Long campaignId) {
        return commentRepo.findAllById(campaignId);
    }*/

   /* public void rateComment(CommentRateDto commentRate) {
        Optional<Comment> comment = commentRepo.findById(commentRate.getCommentId());

        if (comment.isPresent()) {
            comment.get().
        }
    }*/
}
