package com.fantastictrio.cw4sem.service;

import com.fantastictrio.cw4sem.dto.StatisticDecisionPayload;
import com.fantastictrio.cw4sem.model.StatisticDecision;
import com.fantastictrio.cw4sem.repository.StatisticDecisionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StatisticDecisionService {
    private final StatisticDecisionRepository repository;
    private final UserService userService;

    public List<StatisticDecision> findAll() {
        int id = userService.findSelf().getId();
        return repository.findByUserId(id);
    }

    public StatisticDecision findById(int id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }

    public StatisticDecision add(StatisticDecisionPayload payload) {
        StatisticDecision decision = StatisticDecision.builder()
                .data(payload.getData())
                .name(payload.getName())
                .user(userService.findSelf())
                .build();
        return repository.save(decision);
    }
}
