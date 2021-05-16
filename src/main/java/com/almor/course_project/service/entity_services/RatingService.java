package com.almor.course_project.service.entity_services;

import com.almor.course_project.repos.UsersRatingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

    @Autowired
    private UsersRatingRepo usersRatingRepo;

    public Integer getAverageCampaignRating(Long campaignId) {
        return usersRatingRepo.findAvgCampaignRating(campaignId);
    }

    public Integer getUserCampaignRating(Long campaignId, Long userId) {
        return usersRatingRepo.findUserCampaignRating(campaignId, userId);
    }

}
