package com.fantastictrio.cw4sem.controller

import com.fantastictrio.cw4sem.dto.DecisionPayload
import com.fantastictrio.cw4sem.model.Decision
import com.fantastictrio.cw4sem.service.DecisionService
import com.fantastictrio.cw4sem.service.OrganizationService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/decisions")
class DecisionController(
    private val decisionService: DecisionService,
    private val organizationService: OrganizationService
) {
    @get:GetMapping
    @get:PreAuthorize("isAuthenticated()")
    val decisions: List<Decision>
        get() = decisionService.decisions

    @PostMapping("/update/{id}")
    @PreAuthorize("isAuthenticated()")
    fun updateById(@RequestBody payload: DecisionPayload, @PathVariable id: Int): Decision? {
        val org = payload.organizationId?.let { organizationService.findById(it) }
        return decisionService.update(Decision(payload, org, id))
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    fun findById(@PathVariable("id") id: Int): Decision {
        return decisionService.findById(id)
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    fun deleteById(@PathVariable("id") id: Int) {
        return decisionService.deleteById(id)
    }

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    fun add(@RequestBody payload: DecisionPayload): Decision? {
        val org = payload.organizationId?.let {
            organizationService.findById(it)
        }
        return decisionService.update(Decision(payload,org))
    }
}
