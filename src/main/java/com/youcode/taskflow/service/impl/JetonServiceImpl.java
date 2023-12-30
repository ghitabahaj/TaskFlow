package com.youcode.taskflow.service.impl;

import com.youcode.taskflow.entities.Jeton;
import com.youcode.taskflow.repository.JetonRepository;
import com.youcode.taskflow.service.JetonService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;


@Component
@RequiredArgsConstructor
public class JetonServiceImpl implements JetonService {

    private  final JetonRepository jetonRepository;
    @Override
    public boolean canReplaceTask(Long userId) {

        Jeton jeton = jetonRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Jeton not found for user: " + userId));

        return jeton.getDailyReplacementTokens() > 0  && jeton.getModificationRequests() < 2 ;
    }

    @Override
    public void deductReplacementToken(Long userId) {

        Jeton jeton = jetonRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Jeton not found for user: " + userId));

        if (jeton.getDailyReplacementTokens() > 0) {
            jeton.setDailyReplacementTokens(jeton.getDailyReplacementTokens() - 1);
            jetonRepository.save(jeton);
        } else {
            throw new IllegalStateException("No daily replacement tokens available.");
        }
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void doubleModificationTokens() {
        List<Jeton> allJetons = jetonRepository.findAll();
        for (Jeton jeton : allJetons) {
            if (jeton.getModificationRequests() > 0 && jeton.getLastModificationResponse() == null
                    || Duration.between(jeton.getLastModificationResponse(), LocalDateTime.now()).toHours() > 12) {
                jeton.setDailyReplacementTokens(jeton.getDailyReplacementTokens() * 2);
                jetonRepository.save(jeton);
            }
        }
    }
}
