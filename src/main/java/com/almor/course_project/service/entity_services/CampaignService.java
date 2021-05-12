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

    public CampaignDto getCampaign(String campaignName) {

        Optional<Campaign> campaign = campaignRepo.findByName(campaignName);

        return Mappers.getMapper(CampaignMapping.class).entityToDto(campaign.get());
    }

    private void attachBonuses(Campaign campaign) {
        for (Bonus bonus : campaign.getBonuses()) {
            bonus.setCampaign(campaign);
        }
    }

    private void attachPictures(Campaign campaign) {
        for (Gallery gallery : campaign.getPictures()) {
            gallery.setCampaign(campaign);
        }
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


    //refactor
    public void updateCampaign(CampaignDto campaignDto) {

        Campaign campaign = Mappers.getMapper(CampaignMapping.class).dtoToEntity(campaignDto);

        Campaign ogCampaign = campaignRepo.findById(campaign.getId()).get();

        ogCampaign.setName(campaign.getName());
        ogCampaign.setDescription(campaign.getDescription());
        ogCampaign.setLastDateOfCampaign(campaign.getLastDateOfCampaign());
        ogCampaign.setLastUpdateDate(campaign.getLastUpdateDate());
        ogCampaign.setSumOfMoney(campaign.getSumOfMoney());
        ogCampaign.setTopic(campaign.getTopic());

        ogCampaign.updateTags(campaign.getTags());
        ogCampaign.updateBonuses(campaign.getBonuses());

        attachBonuses(ogCampaign);
        attachPictures(ogCampaign);

        campaignRepo.save(ogCampaign);
    }

    public void createCampaign(CampaignDto dtoCampaign) {

        Campaign campaign = Mappers.getMapper(CampaignMapping.class).dtoToEntity(dtoCampaign);

        attachBonuses(campaign);
        attachPictures(campaign);

        campaignRepo.save(campaign);
    }

    public List<CampaignRatingDto> getMostRatedCampaigns() {
        return campaignRepo.findMostRatedCampaigns(PageRequest.of(0, 3));
    }

    public List<CampaignRatingDto> getLastUpdatedCampaigns() {
        return campaignRepo.findRecentlyUpdatedCampaigns(PageRequest.of(0, 3));
    }

    public List<Gallery> deleteCampaign(CampaignDto campaignDto) {

        Campaign campaign = Mappers.getMapper(CampaignMapping.class).dtoToEntity(campaignDto);

        List<Gallery> pictures = new ArrayList<>();
        pictures.addAll(campaign.getPictures());

        campaignRepo.delete(campaign);

        return pictures;
    }

    public boolean isCampaignExists(String campaignName) {

        if (campaignRepo.findByName(campaignName).isPresent()) {
            return true;
        }
        return false;
    }

}
