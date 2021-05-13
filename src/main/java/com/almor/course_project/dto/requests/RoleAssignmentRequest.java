package com.almor.course_project.dto.requests;

import com.almor.course_project.dto.UserDto;
import lombok.Data;

import java.util.List;

@Data
public class RoleAssignmentRequest {

    private List<UserDto> users;

    private String roleName;

}
