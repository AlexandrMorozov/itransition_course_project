package com.almor.course_project.dto.mappings;

import com.almor.course_project.dto.TagDto;
import com.almor.course_project.model.Tag;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TagMapping {

    Tag fromDtoToModel(TagDto tagDto);
    TagDto fromModelToDto(Tag tag);
    List<TagDto> fromListModelToListDto(List<Tag> tags);




}
