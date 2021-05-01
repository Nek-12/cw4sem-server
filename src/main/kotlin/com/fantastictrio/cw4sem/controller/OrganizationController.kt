package com.fantastictrio.cw4sem.controller

import com.fantastictrio.cw4sem.model.Organization
import com.fantastictrio.cw4sem.model.User
import com.fantastictrio.cw4sem.model.User.UserProjection
import com.fantastictrio.cw4sem.service.OrganizationService
import com.fantastictrio.cw4sem.service.UserService
import lombok.AllArgsConstructor
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/organizations")
class OrganizationController(private val organizationService: OrganizationService) {

    @get:PreAuthorize("hasAuthority('ORGANIZATION:MANAGE')")
    @get:GetMapping
    val organizations: List<Organization>
        get() = organizationService.organizations


    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ORGANIZATION:INTERACT')")
    fun getOrganizationById(@PathVariable("id") id: Int): Organization {
        return organizationService.getOrganizationById(id)
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ORGANIZATION:INTERACT')")
    fun deleteUserById(@PathVariable("id") id: Int): Boolean {
        return organizationService.deleteOrganizationById(id)
    }
    
}
