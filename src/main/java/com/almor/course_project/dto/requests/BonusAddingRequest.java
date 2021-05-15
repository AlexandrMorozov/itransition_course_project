package com.almor.course_project.dto.requests;

import com.almor.course_project.dto.BonusDto;
import lombok.Data;

@Data
public class BonusAddingRequest {

    private BonusDto bonus;

    private Long userId;

}
