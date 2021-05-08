package com.almor.course_project.controller;

import com.almor.course_project.dto.CampaignRatingDto;
import com.almor.course_project.dto.TagsDto;
import com.almor.course_project.model.Campaign;
import com.almor.course_project.repos.CampaignRepo;
import com.almor.course_project.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.ls.LSInput;

import java.util.List;

/*@CrossOrigin(origins = "*", maxAge = 3600)*/
@RestController
public class TagController {
    @Autowired
    CampaignRepo campaignRepo;

    @Autowired
    private TagService tagService;

    @GetMapping("/gettags")
    public /*List<TagDto>*/ TagsDto getAllTags() {
        return new TagsDto(tagService.getAllTags());
    }
}
