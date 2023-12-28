package com.youcode.taskflow.dto;


import com.youcode.taskflow.enums.TaskStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
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


}
