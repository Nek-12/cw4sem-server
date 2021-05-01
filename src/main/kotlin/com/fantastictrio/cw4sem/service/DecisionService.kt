package com.fantastictrio.cw4sem.service

import com.fantastictrio.cw4sem.exception.NotFoundException
import com.fantastictrio.cw4sem.model.Decision
import com.fantastictrio.cw4sem.repository.DecisionRepository
import org.springframework.stereotype.Service


@Service
class DecisionService(private val repo: DecisionRepository) {
    val decisions: List<Decision>
        get() = repo.findAll()

    fun getById(id: Int): Decision {
        return repo.findById(id).orElseThrow { NotFoundException("Decision not found") }
    }

    fun deleteById(id: Int) {
        repo.deleteById(id)
    }

    fun update(decision: Decision): Decision? {
        return repo.save(decision)
    }
}
