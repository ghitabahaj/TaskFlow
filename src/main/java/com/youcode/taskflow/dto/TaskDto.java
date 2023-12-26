package com.youcode.taskflow.dto;

import com.youcode.taskflow.entities.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public class TaskDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @FutureOrPresent(message = "La date de début doit être dans le présent ou le futur.")
    @NotNull(message = "La date de fin ne peut pas être vide.")
    private LocalDate startDate;

    @NotEmpty(message = "Au moins un tag est requis.")
    private List<String> tags;

    @Future(message = "La date de fin doit être dans le futur.")
    @NotNull(message = "La date de fin ne peut pas être vide.")
    private LocalDate endDate;


    @NotNull(message = "La tâche doit être marquée comme terminée avant la date limite.")
    private Boolean completed;

    private User assignedUser;
}
