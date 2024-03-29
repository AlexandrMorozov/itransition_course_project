package com.almor.course_project.repos;

import com.almor.course_project.model.composite_tables.UsersRatings;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UsersRatingRepo extends CrudRepository<UsersRatings, Long> {

    @Query("SELECT AVG(ur.rating) FROM UsersRatings ur " +
            "WHERE ur.id.campaignid = ?1 GROUP BY ur.id.campaignid")
    Integer findAvgCampaignRating(Long campaignId);

    @Query("SELECT ur.rating FROM UsersRatings ur WHERE ur.id.campaignid = ?1 AND ur.id.userid = ?2")
    Integer findUserCampaignRating(Long campaignId, Long userId);
}
