package com.almor.course_project.repos;

import com.almor.course_project.model.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepo extends CrudRepository<Comment, Long> {

    List<Comment> findAllById(Long campaignId);

    @Query("SELECT cm.id, cm.text, cm.commentAuthor.name," +
            "(SELECT ul.isLike FROM UsersLikes ul WHERE ul.id.commentId = ?1 AND ul.id.userId = ?2) AS userRate," +
            "(SELECT COUNT(ul.isLike) FROM UsersLikes ul WHERE ul.id.commentId = ?1 AND ul.isLike = true) AS numOfLikes," +
            "(SELECT COUNT(ul.isLike) FROM UsersLikes ul WHERE ul.id.commentId = ?1 AND ul.isLike = false) AS numOfDislikes" +
            " FROM Comment cm")
    String find(Long commentId, Long userId);

}
