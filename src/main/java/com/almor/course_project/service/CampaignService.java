package com.almor.course_project.service;

import com.almor.course_project.dto.CampaignDto;
import com.almor.course_project.dto.CampaignRatingDto;
import com.almor.course_project.dto.mappings.CampaignMapping;
import com.almor.course_project.model.Campaign;
import com.almor.course_project.repos.CampaignRepo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public class CampaignService {

    @Autowired
    private CampaignRepo campaignRepo;

    public CampaignDto getCampaign(String campaignName) {
        Optional<Campaign> campaign = campaignRepo.findByName(campaignName);
        return Mappers.getMapper(CampaignMapping.class).entityToDto(campaign.get());
    }

    public boolean addCampaign(CampaignDto campaignDto) {
        if (!isCampaignExists(campaignDto.getName())) {
            Campaign campaign = Mappers.getMapper(CampaignMapping.class).dtoToEntity(campaignDto);
            campaignRepo.save(campaign);
            return true;
        }
        return false;
    }

    public void alterCampaign(CampaignDto campaignDto) {
        if (!isCampaignExists(campaignDto.getName())) {
        }
    }

    public List<CampaignRatingDto> getMostRatedCampaigns() {
        return campaignRepo.findMostRatedCampaigns(PageRequest.of(0, 5));
    }

    public List<CampaignDto> getLastUpdatedCampaigns() {
        List<Campaign> lastUpdatedCampaigns = campaignRepo.
                findRecentlyUpdatedCampaigns(PageRequest.of(0, 5));
       // System.out.println( Mappers.getMapper(CampaignMapping.class).fromListModelToListDto(lastUpdatedCampaigns));

        return  Mappers.getMapper(CampaignMapping.class).fromListModelToListDto(lastUpdatedCampaigns);
    }

    public void deleteCampaigns(CampaignDto[] campaignsDto) {
        for (int i = 0; i < campaignsDto.length; i++) {
            Campaign campaign = Mappers.getMapper(CampaignMapping.class).dtoToEntity(campaignsDto[i]);
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
