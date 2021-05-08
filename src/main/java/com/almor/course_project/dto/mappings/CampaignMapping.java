package com.almor.course_project.dto.mappings;

import com.almor.course_project.dto.CampaignDto;
import com.almor.course_project.model.Campaign;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CampaignMapping {
    CampaignDto entityToDto(Campaign campaign);
    Campaign dtoToEntity(CampaignDto campaignDto);

    List<CampaignDto> fromListModelToListDto(List<Campaign> campaigns);
}
