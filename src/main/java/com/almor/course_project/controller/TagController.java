package com.almor.course_project.controller;

import com.almor.course_project.dto.TagDto;
import com.almor.course_project.repos.CampaignRepo;
import com.almor.course_project.service.entity_services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {

    @Autowired
    CampaignRepo campaignRepo;

    @Autowired
    private TagService tagService;

    @GetMapping("/gettags")
    public List<TagDto> getAllTags() {
        return tagService.getAllTags();
    }
}
