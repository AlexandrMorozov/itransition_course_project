package com.almor.course_project.service;

import com.almor.course_project.dto.CommentDto;
import com.almor.course_project.dto.CommentRateDto;
import com.almor.course_project.model.Comment;
import com.almor.course_project.model.User;
import com.almor.course_project.repos.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepo commentRepo;

    public void addComment(CommentDto newComment, User user) {
        Comment comment = new Comment();
        comment.setText(newComment.getComment());
        comment.setCommentAuthor(user);
    }

    public List<Comment> receiveCampaignComments(Long campaignId) {
        return commentRepo.findAllById(campaignId);
    }

   /* public void rateComment(CommentRateDto commentRate) {
        Optional<Comment> comment = commentRepo.findById(commentRate.getCommentId());

        if (comment.isPresent()) {
            comment.get().
        }
    }*/
}
