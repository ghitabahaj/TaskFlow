package com.youcode.taskflow.Mapper;


import com.youcode.taskflow.dto.TaskDto;
import com.youcode.taskflow.entities.Task;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {UserMapper.class, TaskTagMapper.class})
public interface TaskMapper {
   TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    TaskDto entityToDto(Task task ,@Context CycleAvoidingMappingContext context);

    @Mapping(target = "assignedUser", ignore = true)
    Task dtoToEntity(TaskDto taskDto,@Context CycleAvoidingMappingContext context);
}
