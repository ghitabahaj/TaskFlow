package com.youcode.taskflow.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Jeton {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private int dailyReplacementTokens;

    private int monthlyDeletionTokens;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "modification_requests")
    private Integer  modificationRequests;

    @Column(name = "last_modification_response")
    private LocalDateTime lastModificationResponse;
}
