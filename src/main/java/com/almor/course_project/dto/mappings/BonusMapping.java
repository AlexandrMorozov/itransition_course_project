package com.almor.course_project.dto.mappings;

import com.almor.course_project.dto.BonusDto;
import com.almor.course_project.model.Bonus;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BonusMapping {

    Bonus dtoToEntity(BonusDto bonusDto);

}
