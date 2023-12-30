package com.youcode.taskflow.Mapper;

import com.youcode.taskflow.dto.UserDto;
import com.youcode.taskflow.entities.User;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {TaskMapper.class})
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDto entityToDto(User user,@Context CycleAvoidingMappingContext context);
    User dtoToEntity(UserDto userDTO,@Context CycleAvoidingMappingContext context);
}