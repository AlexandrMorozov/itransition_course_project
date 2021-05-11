package com.almor.course_project.dto.mappings;

import com.almor.course_project.dto.UserDto;
import com.almor.course_project.dto.UserDtoLite;
import com.almor.course_project.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapping {

    UserDto entityToDto(User user);

    User dtoToEntity(UserDto userDto);

    List<User> fromListDtoToListModel(List<UserDto> usersDto);

    List<UserDto> fromListModelToListDto(List<User> users);

    User dtoLiteToEntity(UserDtoLite userDtoLite);

    UserDtoLite EntityToDtoLite(User user);

}
