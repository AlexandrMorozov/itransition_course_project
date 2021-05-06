package com.almor.course_project.dto.mappings;

import com.almor.course_project.dto.UserDto;
import com.almor.course_project.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapping {
    UserDto entityToDto(User user);
    User dtoToEntity(UserDto userDto);

}
