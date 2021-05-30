package com.fantastictrio.cw4sem.repository;

import com.fantastictrio.cw4sem.model.Decision;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DecisionRepository extends JpaRepository<Decision, Integer> {
    // @Query("select d from decision d where organization.id = :id")
    List<Decision> findByUserId(int id);
}
