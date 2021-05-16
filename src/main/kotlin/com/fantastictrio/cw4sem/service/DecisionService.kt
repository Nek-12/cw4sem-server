package com.fantastictrio.cw4sem.service

import com.fantastictrio.cw4sem.dto.DecisionPayload
import com.fantastictrio.cw4sem.exception.NotFoundException
import com.fantastictrio.cw4sem.model.Decision
import com.fantastictrio.cw4sem.repository.DecisionRepository
import com.fantastictrio.cw4sem.repository.UserRepository
import org.springframework.stereotype.Service


@Service
class DecisionService(
    private val repo: DecisionRepository,
    private val userRepository: UserRepository
) {
    val decisions: List<Decision>
        get() = repo.findAll()

    fun findById(id: Int): Decision {
        return repo.findById(id).orElseThrow { NotFoundException("Decision not found") }
    }

    fun findByUserId(id: Int): List<Decision> {
        return repo.findByUserId(id)
    }

    fun add(payload: DecisionPayload): Decision {
        val owner = userRepository.getById(payload.userId)
        return repo.save(Decision(payload, owner, emptyList()))
    }

    fun deleteById(id: Int) {
        repo.deleteById(id)
    }

    fun update(payload: DecisionPayload, id: Int): Decision? {
        //remove all records
        val decision = findById(id)
        val withoutRecords = Decision(payload, decision.user, emptyList(), id)
        return repo.save(withoutRecords)
    }
}
