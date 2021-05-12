package com.almor.course_project.dto.mappings;

import com.almor.course_project.dto.TopicDto;
import com.almor.course_project.model.Topic;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TopicMapping {

    Topic fromDtoToModel(TopicDto topicDto);//
    TopicDto fromModelToDto(Topic topic);
    List<TopicDto> fromListModelToListDto(List<Topic> topics);

}
