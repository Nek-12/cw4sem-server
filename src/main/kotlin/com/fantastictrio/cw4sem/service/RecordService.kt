package com.fantastictrio.cw4sem.service

import com.fantastictrio.cw4sem.exception.NotFoundException
import com.fantastictrio.cw4sem.model.DecisionRecord
import com.fantastictrio.cw4sem.repository.RecordRepository
import org.springframework.stereotype.Service

@Service
class RecordService(
    private val recordRepository: RecordRepository,
) {
    val records: List<DecisionRecord>
        get() = recordRepository.findAll()

    fun save(record: DecisionRecord): DecisionRecord {
        return recordRepository.save(record)
    }

    fun findById(id: Int): DecisionRecord {
        return recordRepository.findById(id).orElseThrow { NotFoundException("Record not found") }
    }

    fun deleteById(id: Int) {
        recordRepository.deleteById(id)
    }
}
