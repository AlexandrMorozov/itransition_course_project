package com.almor.course_project.dto.requests;

import com.almor.course_project.dto.UserDto;
import lombok.Data;

import java.util.List;

@Data
public class UserStatusChangeRequest {

    List<UserDto> users;

    boolean newStatus;

}
