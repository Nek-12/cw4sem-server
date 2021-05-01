package com.fantastictrio.cw4sem.service

import com.fantastictrio.cw4sem.exception.NotFoundException
import com.fantastictrio.cw4sem.model.Organization
import com.fantastictrio.cw4sem.repository.OrganizationRepository
import org.springframework.stereotype.Service

@Service
class OrganizationService(
    private val repo: OrganizationRepository
) {
    val organizations: List<Organization>
        get() = repo.findAll()

    fun getById(id: Int): Organization {
        return repo.findById(id).orElseThrow { NotFoundException("Organization not found") }
    }

    fun deleteById(id: Int): Boolean {
        repo.deleteById(id)
        return true
    }

    fun update(organization: Organization): Organization? {
        return repo.save(organization)
    }
}
