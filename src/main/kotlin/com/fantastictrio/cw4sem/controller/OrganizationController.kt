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
    @get:GetMapping
    val organizations: List<Organization>
        get() = organizationService.organizations

    @PostMapping("/update/{id}")
    @PreAuthorize("isAuthenticated()")
    fun updateById(@RequestBody payload: OrganizationPayload, @PathVariable("id") id: Int): Organization? {
        val users: List<User> = userService.findByOrganizationId(id) ?: emptyList()
        return organizationService.update(Organization(payload, id, users))
    }


    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    fun findById(@PathVariable("id") id: Int): Organization {
        return organizationService.findById(id)
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    fun deleteById(@PathVariable("id") id: Int) {
        organizationService.deleteById(id)
    }

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    fun add(@RequestBody payload: OrganizationPayload): Organization? {
        return organizationService.update(Organization(payload))
    }
}
