package com.almor.course_project.service.entity_services;

import com.almor.course_project.dto.CampaignDto;
import com.almor.course_project.dto.CampaignRatingDto;
import com.almor.course_project.dto.mappings.CampaignMapping;
import com.almor.course_project.model.*;
import com.almor.course_project.repos.CampaignRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CampaignService {

    @Autowired
    private CampaignRepo campaignRepo;

    public /*CampaignDto*/Campaign getCampaign(String campaignName) {

        Optional<Campaign> campaign = campaignRepo.findByName(campaignName);

        return campaign.get()/*Mappers.getMapper(CampaignMapping.class).entityToDto(campaign.get())*/;
    }

    //refactor
    public void updateCampaign(Campaign campaign) {

        campaign.assignTags();
        campaign.assignPictures();
        campaign.assignBonuses();

        Campaign ogCampaign = campaignRepo.findById(campaign.getId()).get();

        ogCampaign.setName(campaign.getName());
        ogCampaign.setDescription(campaign.getDescription());
        ogCampaign.setLastDateOfCampaign(campaign.getLastDateOfCampaign());
        ogCampaign.setLastUpdateDate(campaign.getLastUpdateDate());
        ogCampaign.setSumOfMoney(campaign.getSumOfMoney());
        ogCampaign.setSumOfFundedMoney(campaign.getSumOfFundedMoney());
        ogCampaign.setTopic(campaign.getTopic());

        ogCampaign.updateTags(campaign.getTags());
        ogCampaign.updateBonuses(campaign.getBonuses());
        ogCampaign.updatePictures(campaign.getPictures());

        campaignRepo.save(ogCampaign);
    }

    public void createCampaign(Campaign campaign) {

        campaign.assignBonuses();
        campaign.assignPictures();
        campaign.assignTags();

        campaignRepo.save(campaign);
    }


    public List<Gallery> deleteCampaign(CampaignDto campaignDto) {

        Campaign campaign = Mappers.getMapper(CampaignMapping.class).dtoToEntity(campaignDto);

        List<Gallery> pictures = new ArrayList<>();
        pictures.addAll(campaign.getPictures());

        campaignRepo.delete(campaign);

        return pictures;
    }

    public CampaignDto deserializeCampaign(String serializedCampaign) {

        CampaignDto resultCampaign = null;

        try {
            resultCampaign = new ObjectMapper().readValue(serializedCampaign, CampaignDto.class);
        } catch (Exception e) {

        }

        return resultCampaign;
    }

    public void receivePayment(Long campaignId, int sumOfMoney) {

        //ref
        /*Optional<Campaign>*/Campaign campaign = campaignRepo.findById(campaignId).get();

       /* if (campaign.isPresent()) {

            campaign.get().addSum(sumOfMoney);
            campaignRepo.save(campaign.get());
        }*/

        campaign.addSum(sumOfMoney);
        campaignRepo.save(campaign);

        //return false;
    }

    public List<CampaignRatingDto> getMostRatedCampaigns() {
        return campaignRepo.findMostRatedCampaigns(PageRequest.of(0, 3));
    }

    public List<CampaignRatingDto> getLastUpdatedCampaigns() {
        return campaignRepo.findRecentlyUpdatedCampaigns(PageRequest.of(0, 3));
    }

    public boolean isCampaignExists(String campaignName) {

        if (campaignRepo.findByName(campaignName).isPresent()) {
            return true;
        }
        return false;
    }

}
