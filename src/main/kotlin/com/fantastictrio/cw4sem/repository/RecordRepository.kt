package com.fantastictrio.cw4sem.repository

import com.fantastictrio.cw4sem.model.DecisionRecord
import org.springframework.data.jpa.repository.JpaRepository

interface RecordRepository : JpaRepository<DecisionRecord, Int> {
    fun deleteByDecisionId(id: Int)
}
