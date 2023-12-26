package com.youcode.taskflow.repository;

import com.youcode.taskflow.entities.Jeton;
import com.youcode.taskflow.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface JetonRepository extends JpaRepository<Jeton, Long> {
    Optional<Jeton> findByUserId(Long userId);

}
