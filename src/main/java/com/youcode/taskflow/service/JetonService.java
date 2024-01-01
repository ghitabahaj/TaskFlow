package com.youcode.taskflow.service;


import org.springframework.stereotype.Service;

@Service
public interface JetonService {
    boolean canReplaceTask(Long userId);

    void deductReplacementToken(Long userId);

    void deductDeletionToken(Long userId);
    void doubleModificationTokens(Long UserId);

}
