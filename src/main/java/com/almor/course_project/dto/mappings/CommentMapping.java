package com.almor.course_project.dto.mappings;

import com.almor.course_project.dto.CommentDto;
import com.almor.course_project.model.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapping {

    Comment dtoToEntity(CommentDto commentDto);
}
