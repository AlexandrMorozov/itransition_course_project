package com.almor.course_project.service;

import com.almor.course_project.dto.CampaignDto;
import com.almor.course_project.dto.CampaignRatingDto;
import com.almor.course_project.dto.mappings.CampaignMapping;
import com.almor.course_project.model.Bonus;
import com.almor.course_project.model.Campaign;
import com.almor.course_project.model.Gallery;
import com.almor.course_project.model.Tag;
import com.almor.course_project.repos.CampaignRepo;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    private void attachTags(Campaign campaign) {
        for (Tag tag : campaign.getTags()) {
            tag.setCampaign(campaign);
        }
    }

    public void attachImages(List<Gallery> imageLinks, Long userId) {

        Optional<Campaign> campaign = campaignRepo.findById(userId);

        if (campaign.isPresent()) {

            for (Gallery image : imageLinks) {
                image.setCampaign(campaign.get());
            }

            Set<Gallery> gallerySet = new HashSet<>(imageLinks);

            campaign.get().setPictures(gallerySet);

            campaignRepo.save(campaign.get());

        }

    }


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

        attachTags(ogCampaign);
        attachBonuses(ogCampaign);

        campaignRepo.save(ogCampaign);
    }

    public Long createCampaign(CampaignDto dtoCampaign) {

        Campaign campaign = Mappers
                .getMapper(CampaignMapping.class)
                .dtoToEntity(dtoCampaign);

        attachBonuses(campaign);
        attachTags(campaign);

        campaign = campaignRepo.save(campaign);

        return campaign.getId();
    }

    public List<CampaignRatingDto> getMostRatedCampaigns() {
        return campaignRepo.findMostRatedCampaigns(PageRequest.of(0, 5));
    }

    public List<CampaignDto> getLastUpdatedCampaigns() {
        List<Campaign> lastUpdatedCampaigns = campaignRepo.
                findRecentlyUpdatedCampaigns(PageRequest.of(0, 5));

        List<CampaignDto> campaigns = Mappers
                .getMapper(CampaignMapping.class)
                .fromListModelToListDto(lastUpdatedCampaigns);

        return campaigns;
    }

    public void deleteCampaigns(CampaignDto[] campaignsDto) {
        for (int i = 0; i < campaignsDto.length; i++) {

            Campaign campaign = Mappers
                    .getMapper(CampaignMapping.class)
                    .dtoToEntity(campaignsDto[i]);

            campaignRepo.delete(campaign);
        }
    }

    public boolean isCampaignExists(String campaignName) {

        if (campaignRepo.findByName(campaignName).isPresent()) {
            return true;
        }
        return false;
    }

}
