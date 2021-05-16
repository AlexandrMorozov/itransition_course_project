package com.almor.course_project.service.service_interfaces;

import com.almor.course_project.dto.CampaignDto;
import com.almor.course_project.dto.CampaignRatingDto;
import com.almor.course_project.model.Campaign;
import com.almor.course_project.model.Gallery;

import java.util.List;

public interface CampaignService {

    Campaign getCampaign(String campaignName);

    void updateCampaign(Campaign updatedCampaign);

    void createCampaign(Campaign campaign);

    List<Gallery> deleteCampaign(CampaignDto campaignDto);

    CampaignDto deserializeCampaign(String serializedCampaign);

    void receivePayment(Long campaignId, int sumOfMoney);

    List<CampaignRatingDto> getMostRatedCampaigns();

    List<CampaignRatingDto> getLastUpdatedCampaigns();

    boolean isCampaignExists(String campaignName);

}
