package com.almor.course_project.service.service_interfaces;

import com.almor.course_project.dto.TagDto;
import com.almor.course_project.model.Tag;


import java.util.List;
import java.util.Set;

public interface TagService {

    List<TagDto> getAllTags();

    Set<Tag> addNewTags(Set<Tag> newTags);
}
