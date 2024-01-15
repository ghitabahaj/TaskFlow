package com.youcode.taskflow.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youcode.taskflow.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;



@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private LocalDate startDate;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
    private List<TaskTag> taskTags;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    private String description;

    private TaskStatus taskstatus;

    private boolean replaced;
    private boolean deleted;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User assignedUser;

    @ManyToOne
    @JoinColumn(name = "created_by_user_id")
    private User createdBy;




}
