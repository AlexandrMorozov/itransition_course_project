package com.almor.course_project.service;

import com.almor.course_project.dto.TopicDto;
import com.almor.course_project.dto.mappings.TopicMapping;
import com.almor.course_project.repos.TopicRepo;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    @Autowired
    private TopicRepo topicRepo;

    public List<TopicDto> getAllTopics() {

        List<TopicDto> dtoTopics = Mappers.getMapper(TopicMapping.class)
                .fromListModelToListDto(topicRepo.findAll());

        return dtoTopics;
    }

}
