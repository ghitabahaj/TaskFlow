package com.youcode.taskflow.Mapper;

import com.youcode.taskflow.dto.UserDto;
import com.youcode.taskflow.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDto entityToDto(User user);
    User dtoToEntity(UserDto userDTO);
}