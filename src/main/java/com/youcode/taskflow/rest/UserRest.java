package com.youcode.taskflow.rest;


import com.youcode.taskflow.entities.User;
import com.youcode.taskflow.service.TaskService;
import com.youcode.taskflow.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserRest {

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {

            User createdUser = userService.addUser(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);

    }


    @PostMapping("/modify")
    public ResponseEntity<String> requestTaskModification(@RequestParam Long taskId, @RequestParam Long userId) {
        taskService.requestTaskModification(taskId, userId);
        return ResponseEntity.ok("Modification request sent successfully.");
    }
}
