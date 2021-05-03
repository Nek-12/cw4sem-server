package com.fantastictrio.cw4sem.controller

import com.fantastictrio.cw4sem.dto.OrganizationPayload
import com.fantastictrio.cw4sem.model.Organization
import com.fantastictrio.cw4sem.model.User
import com.fantastictrio.cw4sem.service.OrganizationService
import com.fantastictrio.cw4sem.service.UserService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/organizations")
class OrganizationController(
    private val organizationService: OrganizationService,
    private val userService: UserService
) {

    @get:PreAuthorize("hasAuthority('ORGANIZATION:MANAGE')")
    @get:GetMapping
    val organizations: List<Organization>
        get() = organizationService.organizations

    @PostMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ORGANIZATION:MANAGE')")
    fun updateById(@RequestBody payload: OrganizationPayload, @PathVariable("id") id: Int): Organization? {
        val users: List<User> = userService.getByOrganizationId(id) ?: emptyList()
        return organizationService.update(Organization(payload, id, users))
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ORGANIZATION:MANAGE')")
    fun getById(@PathVariable("id") id: Int): Organization {
        return organizationService.getById(id)
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ORGANIZATION:MANAGE')")
    fun deleteById(@PathVariable("id") id: Int) {
        organizationService.deleteById(id)
    }
}
