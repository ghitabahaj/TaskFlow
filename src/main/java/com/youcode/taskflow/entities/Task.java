package com.youcode.taskflow.entities;

import com.youcode.taskflow.enums.Status;
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

    private LocalDate startDate;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<TaskTag> taskTags;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    private String description;

    private Status Taskstatus;

    private boolean replaced;
    private boolean deleted;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User assignedUser;

}
