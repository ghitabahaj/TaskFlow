package com.youcode.taskflow.rest;

import com.youcode.taskflow.Mapper.CycleAvoidingMappingContext;
import com.youcode.taskflow.Mapper.TaskMapper;
import com.youcode.taskflow.dto.TaskDto;
import com.youcode.taskflow.entities.Task;
import com.youcode.taskflow.enums.RequestStatus;
import com.youcode.taskflow.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/tasks")
public class TaskRest {


    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TaskRest(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper=taskMapper;
    }

    @GetMapping("/all")
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        CycleAvoidingMappingContext context = new CycleAvoidingMappingContext();
        List<Task> tasks = taskService.findAll();
        List<TaskDto> taskDtos = tasks.stream()
                .map(task -> taskMapper.entityToDto(task, context))
                .collect(Collectors.toList());

        return new ResponseEntity<>(taskDtos, HttpStatus.OK);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable Long taskId) {
        CycleAvoidingMappingContext context = new CycleAvoidingMappingContext();
        Task task = taskService.findById(taskId);
        TaskDto taskDto = taskMapper.entityToDto(task, context);

        return new ResponseEntity<>(taskDto, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<TaskDto> createTask(
            @RequestBody TaskDto taskRequestDto,
            @RequestParam Long userId,
            @RequestParam List<String> tagNames) {
        CycleAvoidingMappingContext context = new CycleAvoidingMappingContext();
        Task task = taskMapper.dtoToEntity(taskRequestDto, context);
        Task createdTask = taskService.createTask(task, userId, tagNames);
        TaskDto taskDto = taskMapper.entityToDto(createdTask, context);

        return new ResponseEntity<>(taskDto, HttpStatus.CREATED);
    }

    @PutMapping("/update/{taskId}")
    public ResponseEntity<TaskDto> updateTask(
            @PathVariable Long taskId,
            @RequestBody TaskDto updatedTaskDto) {
        CycleAvoidingMappingContext context = new CycleAvoidingMappingContext();
        Task updatedTask = taskMapper.dtoToEntity(updatedTaskDto, context);
        Task updatedTaskResult = taskService.updateTask(taskId, updatedTask);
        TaskDto taskDto = taskMapper.entityToDto(updatedTaskResult, context);

        return new ResponseEntity<>(taskDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return new ResponseEntity<>("Task deleted successfully.", HttpStatus.OK);
    }

    @PostMapping("/request-modification/{taskId}/{userId}")
    public ResponseEntity<String> requestTaskModification(
            @PathVariable Long taskId,
            @PathVariable Long userId) {
        taskService.requestTaskModification(taskId, userId);
        return new ResponseEntity<>("Modification request sent successfully.", HttpStatus.OK);
    }


}
