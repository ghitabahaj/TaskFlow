package com.youcode.taskflow.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
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
}
