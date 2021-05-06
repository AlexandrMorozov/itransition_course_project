package com.almor.course_project.controller;

import com.almor.course_project.dto.TagDto;
import com.almor.course_project.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("test2")
    public List<TagDto> getAllTags() {
        return tagService.getAllTags();
    }
}
