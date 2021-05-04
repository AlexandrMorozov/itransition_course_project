package com.almor.course_project.dto;

import lombok.Data;

@Data
public class CommentDto {

    private Long id;

    private String comment;

    private UserDto commentAuthor;

}
