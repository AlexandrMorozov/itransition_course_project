package com.almor.course_project.service;

import com.almor.course_project.dto.TagDto;
import com.almor.course_project.dto.mappings.TagMapping;
import com.almor.course_project.model.Tag;
import com.almor.course_project.repos.TagRepo;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    @Autowired
    private TagRepo tagRepo;

    public List<TagDto> getAllTags() {
        List<TagDto> dtoTags = Mappers.getMapper(TagMapping.class)
                .fromListModelToListDto(tagRepo.findAll());

        return dtoTags;
    }

}
