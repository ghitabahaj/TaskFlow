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
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.findAll();

        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long taskId) {

        Task task = taskService.findById(taskId);

        return new ResponseEntity<>(task, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<TaskDto> createTask(
            @RequestBody TaskDto taskDto,
            @RequestParam Long userId,
            @RequestParam List<String> tagNames) {

        Task task = taskMapper.dtoToEntity(taskDto);

        Task createdTask = taskService.createTask(task, userId, tagNames);

        TaskDto createdTaskDto = taskMapper.entityToDto(createdTask);

        return new ResponseEntity<>(createdTaskDto, HttpStatus.CREATED);
    }

    @PutMapping("/update/{taskId}")
    public ResponseEntity<Task> updateTask(
            @PathVariable Long taskId,
            @RequestBody Task updatedTask) {
        Task updatedTaskResult = taskService.updateTask(taskId, updatedTask);

        return new ResponseEntity<>(updatedTaskResult, HttpStatus.OK);
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
