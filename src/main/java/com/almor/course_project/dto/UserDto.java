package com.almor.course_project.dto;

import lombok.Data;

@Data
public class UserDto {

    public UserDto(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    private String name;

    private String password;

    private String email;
}
