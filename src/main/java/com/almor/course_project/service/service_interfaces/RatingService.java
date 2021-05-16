package com.almor.course_project.service.service_interfaces;

public interface RatingService {

    Integer getAverageCampaignRating(Long campaignId);

    Integer getUserCampaignRating(Long campaignId, Long userId);

}
