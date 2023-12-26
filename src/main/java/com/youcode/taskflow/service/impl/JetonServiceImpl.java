package com.youcode.taskflow.service.impl;

import com.youcode.taskflow.entities.Jeton;
import com.youcode.taskflow.repository.JetonRepository;
import com.youcode.taskflow.service.JetonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class JetonServiceImpl implements JetonService {

    private  final JetonRepository jetonRepository;
    @Override
    public boolean canReplaceTask(Long userId) {

        Jeton jeton = jetonRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Jeton not found for user: " + userId));

        return jeton.getDailyReplacementTokens() > 0;
    }

    @Override
    public void deductReplacementToken(Long userId) {

        Jeton jeton = jetonRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Jeton not found for user: " + userId));

        jeton.setDailyReplacementTokens(jeton.getDailyReplacementTokens() - 1);

        jetonRepository.save(jeton);
    }
}
