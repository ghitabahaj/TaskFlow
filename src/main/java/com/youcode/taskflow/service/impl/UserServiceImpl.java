package com.youcode.taskflow.service.impl;

import com.youcode.taskflow.entities.Jeton;
import com.youcode.taskflow.entities.User;
import com.youcode.taskflow.enums.Role;
import com.youcode.taskflow.repository.JetonRepository;
import com.youcode.taskflow.repository.UserRepository;
import com.youcode.taskflow.service.JetonService;
import com.youcode.taskflow.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JetonRepository jetonRepository;

    @Override
    public User addUser(User user) {

        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty.");
        }

        if (user.getRole() == null) {
            user.setRole(Role.USER);
        }

        User savedUser = userRepository.save(user);


        setInitialTokenValues(savedUser);

        return savedUser;
    }


    private void setInitialTokenValues(User user) {

        Jeton jeton = new Jeton();
        jeton.setDailyReplacementTokens(2);
        jeton.setMonthlyDeletionTokens(1);
        jeton.setUser(user);

        jetonRepository.save(jeton);
    }
}
