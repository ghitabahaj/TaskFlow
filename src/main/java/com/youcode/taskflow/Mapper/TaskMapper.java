package com.youcode.taskflow.Mapper;


import com.youcode.taskflow.dto.TaskDto;
import com.youcode.taskflow.entities.Task;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class, TaskTagMapper.class})
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);


    @Mapping(source = "assignedUser", target = "assignedUser")
    @Mapping(source = "createdBy", target = "createdBy")
    @Mapping(source = "taskTags", target = "taskTags")
    Task dtoToEntity(TaskDto taskDto);


    @Mapping(source = "assignedUser", target = "assignedUser")
    @Mapping(source = "createdBy", target = "createdBy")
    @Mapping(source = "taskTags", target = "taskTags")
    TaskDto entityToDto(Task task);
}