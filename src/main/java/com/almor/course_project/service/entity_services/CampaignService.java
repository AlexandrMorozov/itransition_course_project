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

    public Campaign getCampaign(String campaignName) {

        Optional<Campaign> campaign = campaignRepo.findByName(campaignName);

        return campaign.get();
    }

    public void updateCampaign(Campaign updatedCampaign) {

        updatedCampaign.assignCampaignElements();

        Campaign originalCampaign = campaignRepo.findById(updatedCampaign.getId()).get();

        originalCampaign.setName(updatedCampaign.getName());
        originalCampaign.setDescription(updatedCampaign.getDescription());
        originalCampaign.setLastDateOfCampaign(updatedCampaign.getLastDateOfCampaign());
        originalCampaign.setLastUpdateDate(updatedCampaign.getLastUpdateDate());
        originalCampaign.setSumOfMoney(updatedCampaign.getSumOfMoney());
        originalCampaign.setSumOfFundedMoney(updatedCampaign.getSumOfFundedMoney());
        originalCampaign.setTopic(updatedCampaign.getTopic());

        originalCampaign.updateTags(updatedCampaign.getTags());
        originalCampaign.updateBonuses(updatedCampaign.getBonuses());
        originalCampaign.updatePictures(updatedCampaign.getPictures());//!!!!

        campaignRepo.save(originalCampaign);
    }

    public void createCampaign(Campaign campaign) {

        campaign.assignCampaignElements();
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
            System.out.println(e);
        }

        return resultCampaign;
    }

    public void receivePayment(Long campaignId, int sumOfMoney) {

        Campaign campaign = campaignRepo.findById(campaignId).get();
        campaign.addSum(sumOfMoney);

        campaignRepo.save(campaign);
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
