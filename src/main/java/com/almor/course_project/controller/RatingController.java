package com.almor.course_project.controller;

import com.almor.course_project.service.entity_services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*@Controller*/
@RestController
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @GetMapping
    public Integer getAverageCampaignRating(int campaignId) {
        return ratingService.getAverageCampaignRating(campaignId);
    }

    @GetMapping
    public Integer getUserCampaignRating(int campaignId, int userId) {
        return ratingService.getUserCampaignRating(campaignId, userId);
    }
}
