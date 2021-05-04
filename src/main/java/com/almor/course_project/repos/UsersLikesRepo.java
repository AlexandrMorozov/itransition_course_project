package com.almor.course_project.repos;

import com.almor.course_project.model.composite_tables.UsersLikes;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UsersLikesRepo extends CrudRepository<UsersLikes, Long> {

    @Query("SELECT COUNT(ul.isLike) FROM UsersLikes ul WHERE ul.id.commentId = ?1 AND ul.isLike = ?2")
    int countCommentRates(int commentId, boolean rateValue);



}
