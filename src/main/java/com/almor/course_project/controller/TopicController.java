package com.almor.course_project.controller;

import com.almor.course_project.dto.TopicsDto;
import com.almor.course_project.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicController {

    @Autowired
    private TopicService topicService;

    @GetMapping("/getthemes")
    public /*List<TopicDto>*/TopicsDto getAllTopics() {
        return new TopicsDto(topicService.getAllTopics());
    }

}
