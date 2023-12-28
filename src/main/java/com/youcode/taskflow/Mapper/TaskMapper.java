package com.youcode.taskflow.Mapper;


import com.youcode.taskflow.dto.TaskDto;
import com.youcode.taskflow.entities.Task;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TaskMapper {


   TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    TaskDto entityToDto(Task task);

    Task dtoToEntity(TaskDto userDTO);
}
