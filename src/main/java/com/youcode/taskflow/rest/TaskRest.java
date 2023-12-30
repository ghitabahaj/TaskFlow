package com.youcode.taskflow.rest;

import com.youcode.taskflow.Mapper.CycleAvoidingMappingContext;
import com.youcode.taskflow.Mapper.TaskMapper;
import com.youcode.taskflow.dto.TaskDto;
import com.youcode.taskflow.entities.Task;
import com.youcode.taskflow.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/tasks")
public class TaskRest {


    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TaskRest(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper=taskMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<TaskDto> createTask(
            @RequestBody TaskDto taskRequestDto,
            @RequestParam Long userId,
            @RequestParam List<String> tagNames) {
        CycleAvoidingMappingContext context = new CycleAvoidingMappingContext();
        Task task = taskMapper.dtoToEntity(taskRequestDto,context);
        Task createdTask = taskService.createTask(task, userId, tagNames);
        TaskDto taskDto = taskMapper.entityToDto(createdTask,context);

        return new ResponseEntity<>(taskDto, HttpStatus.CREATED);

    }
}
