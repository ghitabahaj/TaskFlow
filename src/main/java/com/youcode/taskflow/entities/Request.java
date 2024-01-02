package com.youcode.taskflow.entities;


import com.youcode.taskflow.enums.RequestStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    @Column(name = "creation_time")
    @CreationTimestamp
    private LocalDateTime creationTime;

    @ManyToOne
    @JoinColumn(name = "processed_by_user_id")
    private User processedBy;

    private LocalDateTime processedTime;

}