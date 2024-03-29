package com.youcode.taskflow.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youcode.taskflow.entities.User;
import com.youcode.taskflow.enums.TaskStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class TaskDto {

    private Long id;

    @FutureOrPresent(message = "La date de début doit être dans le présent ou le futur.")
    @NotNull(message = "La date de fin ne peut pas être vide.")
    private LocalDate startDate;

    @Future(message = "La date de fin doit être dans le futur.")
    @NotNull(message = "La date de fin ne peut pas être vide.")
    private LocalDate endDate;

    private String description;

    private TaskStatus taskstatus;

    private boolean replaced;

    private boolean deleted;

    private UserDto assignedUser;

    private UserDto createdBy;

    private  List<TaskTagDto> taskTags;


}
