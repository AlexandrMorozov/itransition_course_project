package com.almor.course_project.service.entity_services;

import com.almor.course_project.model.Campaign;
import com.almor.course_project.model.User;
import com.almor.course_project.model.composite_tables.UsersRatings;
import com.almor.course_project.repos.UsersRatingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

    @Autowired
    private UsersRatingRepo usersRatingRepo;

    public void rateCampaign(User user, Campaign ratedCampaign, int ratingValue) {

        UsersRatings userRate = new UsersRatings();
        userRate.setUser(user);
        userRate.setCampaign(ratedCampaign);
        userRate.setRating(ratingValue);

        usersRatingRepo.save(userRate);

    }

    public Integer getAverageCampaignRating(Long campaignId) {
        return usersRatingRepo.findAvgCampaignRating(campaignId);
    }

    public Integer getUserCampaignRating(Long campaignId, Long userId) {
        return usersRatingRepo.findUserCampaignRating(campaignId, userId);
    }



}
