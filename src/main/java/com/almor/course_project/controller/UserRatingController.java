package com.almor.course_project.controller;

import com.almor.course_project.service.entity_services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rating")
public class UserRatingController {

    @Autowired
    private RatingService ratingService;

    @GetMapping("/average")
    public Integer getAverageCampaignRating(Long campaignId) {
        return ratingService.getAverageCampaignRating(campaignId);
    }

    @GetMapping("/user")
    public Integer getUserCampaignRating(Long campaignId, Long userId) {
        return ratingService.getUserCampaignRating(campaignId, userId);
    }

}
