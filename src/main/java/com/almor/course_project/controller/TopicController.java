package com.almor.course_project.controller;

import com.almor.course_project.dto.TopicDto;
import com.almor.course_project.service.service_interfaces.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TopicController {

    @Autowired
    private TopicService topicService;

    @GetMapping("/getthemes")
    public List<TopicDto> getAllTopics() {
        return topicService.getAllTopics();
    }

}
