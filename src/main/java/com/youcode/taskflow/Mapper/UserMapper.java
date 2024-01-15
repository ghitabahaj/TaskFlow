package com.youcode.taskflow.Mapper;

import com.youcode.taskflow.dto.UserDto;
import com.youcode.taskflow.entities.Task;
import com.youcode.taskflow.entities.User;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring" ,  uses = {TaskMapper.class, JetonMapper.class})
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


    @Mapping(source = "tasks", target = "taskIds" , qualifiedByName = "mapTasksToIds")
    @Mapping(source = "jeton", target = "jeton")
    UserDto entityToDto(User user);

    @Named("mapTasksToIds")
    static List<Long> mapTasksToIds(List<Task> tasks) {
        return tasks.stream()
                .map(Task::getId)
                .collect(Collectors.toList());
    }

    User dtoToEntity(UserDto userDTO);

    List<UserDto> entitiesToDtos(List<User> users);

}
