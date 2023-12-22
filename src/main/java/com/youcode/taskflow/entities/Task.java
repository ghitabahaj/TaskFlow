package com.youcode.taskflow.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;



@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @FutureOrPresent(message = "La date de début doit être dans le présent ou le futur.")
    @NotNull(message = "La date de fin ne peut pas être vide.")
    private LocalDate startDate;

    @NotEmpty(message = "Au moins un tag est requis.")
    @ElementCollection
    private List<String> tags;

    @Future(message = "La date de fin doit être dans le futur.")
    @NotNull(message = "La date de fin ne peut pas être vide.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;


    @NotNull(message = "La tâche doit être marquée comme terminée avant la date limite.")
    private Boolean completed;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User assignedUser;



}
