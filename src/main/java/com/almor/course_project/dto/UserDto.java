package com.almor.course_project.dto;

import com.almor.course_project.model.Bonus;
import com.almor.course_project.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    private String name;

    private String password;

    private String email;

    private Set<Bonus> bonuses;

    private Set<Role> roles;
}
