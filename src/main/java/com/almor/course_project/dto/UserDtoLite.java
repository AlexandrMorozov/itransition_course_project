package com.almor.course_project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoLite {

    private Long id;

    private String name;

    private String password;

    private String email;

    private boolean isEnabled;
}
