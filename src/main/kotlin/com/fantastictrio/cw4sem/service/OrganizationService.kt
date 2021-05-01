package com.fantastictrio.cw4sem.service

import com.fantastictrio.cw4sem.exception.NotFoundException
import com.fantastictrio.cw4sem.model.Organization
import com.fantastictrio.cw4sem.repository.OrganizationRepository
import org.springframework.stereotype.Service

@Service
class OrganizationService(
    private val organizationRepository: OrganizationRepository
) {
    val organizations: List<Organization>
        get() = organizationRepository.findAll()

    fun getOrganizationById(id: Int): Organization {
        return organizationRepository.findById(id).orElseThrow {
            NotFoundException("Organization not found")
        }
    }

    fun deleteOrganizationById(id: Int): Boolean {
        organizationRepository.deleteById(id)
        return true
    }
}
