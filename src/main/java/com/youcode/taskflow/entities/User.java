package com.youcode.taskflow.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.youcode.taskflow.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    private String username;


    private Role role;

    @OneToMany(mappedBy = "assignedUser", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Task> tasks;


    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Jeton jeton;


}
