package com.fantastictrio.cw4sem.repository;

import com.fantastictrio.cw4sem.model.StatisticDecision;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatisticDecisionRepository extends JpaRepository<StatisticDecision, Integer> {
    List<StatisticDecision> findByUserId(int id);
}
