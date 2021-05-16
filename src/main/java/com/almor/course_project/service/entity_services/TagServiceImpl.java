package com.almor.course_project.service.entity_services;

import com.almor.course_project.dto.TagDto;
import com.almor.course_project.dto.mappings.TagMapping;
import com.almor.course_project.model.Tag;
import com.almor.course_project.repos.TagRepo;
import com.almor.course_project.service.service_interfaces.TagService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepo tagRepo;

    public List<TagDto> getAllTags() {
        return Mappers.getMapper(TagMapping.class)
                .fromListModelToListDto(tagRepo.findAll());
    }

    public Set<Tag> addNewTags(Set<Tag> newTags) {
        tagRepo.saveAll(newTags);
        return newTags;
    }

}
