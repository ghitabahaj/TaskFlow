package com.youcode.taskflow.Mapper;


import com.youcode.taskflow.dto.TaskDto;
import com.youcode.taskflow.dto.TaskTagDto;
import com.youcode.taskflow.entities.Task;
import com.youcode.taskflow.entities.TaskTag;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TaskTagMapper {
    TaskTagMapper INSTANCE = Mappers.getMapper(TaskTagMapper.class);

    TaskTagDto entityToDto(TaskTag taskTag);

    TaskTag dtoToEntity(TaskTagDto taskTagDto);
}
