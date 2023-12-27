package com.youcode.taskflow.service;


import com.youcode.taskflow.entities.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User addUser(User user);

}
