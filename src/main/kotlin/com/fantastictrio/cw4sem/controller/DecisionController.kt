package com.fantastictrio.cw4sem.controller

import com.fantastictrio.cw4sem.dto.DecisionPayload
import com.fantastictrio.cw4sem.model.Decision
import com.fantastictrio.cw4sem.service.DecisionService
import com.fantastictrio.cw4sem.service.OrganizationService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*


// C
//  OK R
// U
// OK D

@RestController
@RequestMapping("/decisions")
class DecisionController(
    private val decisionService: DecisionService,
    private val organizationService: OrganizationService
) {
    @get:PreAuthorize("hasAuthority('DECISION:MANAGE')")
    @get:GetMapping
    val decisions: List<Decision>
        get() = decisionService.decisions

    @PostMapping("/update/{id}")
    @PreAuthorize("hasAuthority('DECISION:MANAGE')")
    fun updateById(@RequestBody payload: DecisionPayload, @PathVariable id: Int): Decision? {
        val org = organizationService.getById(payload.organizationId)
        return decisionService.update(Decision(payload, id, org))
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('DECISION:MANAGE')")
    fun getById(@PathVariable("id") id: Int): Decision {
        return decisionService.getById(id)
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DECISION:MANAGE')")
    fun deleteById(@PathVariable("id") id: Int) {
        return decisionService.deleteById(id)
    }
}
