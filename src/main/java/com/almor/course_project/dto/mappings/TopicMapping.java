package com.almor.course_project.dto.mappings;

import com.almor.course_project.dto.TagDto;
import com.almor.course_project.dto.TopicDto;
import com.almor.course_project.model.Tag;
import com.almor.course_project.model.Topic;

import java.util.List;

public interface TopicMapping {

    Topic fromDtoToModel(TopicDto topicDto);
    TopicDto fromModelToDto(Topic topic);
    List<TopicDto> fromListModelToListDto(List<Topic> topics);

}
