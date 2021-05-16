package com.almor.course_project.dto.mappings;

import com.almor.course_project.dto.TopicDto;
import com.almor.course_project.model.Topic;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TopicMapping {

    TopicDto fromModelToDto(Topic topic);

    List<TopicDto> fromListModelToListDto(List<Topic> topics);

}
