package com.almor.course_project.dto.requests;

import com.almor.course_project.dto.BonusDto;
import com.almor.course_project.dto.CampaignDto;
import lombok.Data;

@Data
public class BonusAddingRequest {

    private BonusDto bonus;

    private CampaignDto campaign;

    private Long userId;

}
