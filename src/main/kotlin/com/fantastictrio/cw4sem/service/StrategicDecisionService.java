package com.fantastictrio.cw4sem.service;

import com.fantastictrio.cw4sem.dto.StrategicDecisionPayload;
import com.fantastictrio.cw4sem.model.StrategicDecision;
import com.fantastictrio.cw4sem.repository.StrategicDecisionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StrategicDecisionService {
    private final StrategicDecisionRepository repository;
    private final UserService userService;

    public List<StrategicDecision> findAll() {
        int id = userService.findSelf().getId();
        return repository.findByUserId(id);
    }

    public StrategicDecision findById(int id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }

    public StrategicDecision add(StrategicDecisionPayload payload) {
        StrategicDecision decision = StrategicDecision.builder()
                .data(payload.getData())
                .name(payload.getName())
                .user(userService.findSelf())
                .build();
        return repository.save(decision);
    }
}
