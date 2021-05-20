package com.fantastictrio.cw4sem.repository;

import com.fantastictrio.cw4sem.model.StrategicDecision;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StrategicDecisionRepository extends JpaRepository<StrategicDecision, Integer> {
    List<StrategicDecision> findByUserId(int id);
}
