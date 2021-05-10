package com.fantastictrio.cw4sem.controller

import com.fantastictrio.cw4sem.DecisionMaker
import com.fantastictrio.cw4sem.dto.DecisionPayload
import com.fantastictrio.cw4sem.model.Decision
import com.fantastictrio.cw4sem.model.DecisionRecord
import com.fantastictrio.cw4sem.service.DecisionService
import com.fantastictrio.cw4sem.service.OrganizationService
import com.fantastictrio.cw4sem.service.RecordService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/decisions")
class DecisionController(
    private val decisionService: DecisionService,
    private val organizationService: OrganizationService,
    private val recordService: RecordService,
) {
    @get:GetMapping
    @get:PreAuthorize("isAuthenticated()")
    val decisions: List<Decision>
        get() = decisionService.decisions

    @PostMapping("/update/{id}")
    @PreAuthorize("isAuthenticated()")
    fun updateById(@RequestBody payload: DecisionPayload, @PathVariable id: Int): Decision? {
        val org = organizationService.findById(payload.organizationId)
        return decisionService.update(Decision(payload, org, id))
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    fun findById(@PathVariable("id") id: Int): Decision {
        return decisionService.findById(id)
    }

    @GetMapping("/organization/{id}")
    @PreAuthorize("isAuthenticated()")
    fun findByOrganizationId(@PathVariable("id") id: Int): List<Decision> {
        return decisionService.findByOrganizationId(id)
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

    @PostMapping("/make/{id}")
    @PreAuthorize("isAuthenticated()")
    fun make(@PathVariable("id") decisionId: Int, @RequestBody matrix: List<List<Double>>): DecisionRecord {
        val decision = decisionService.findById(decisionId)
        val result = DecisionMaker.make(decision, matrix)
        return recordService.save(result)
    }

    @DeleteMapping("/record/{id}")
    fun deleteRecordById(@PathVariable("id") id: Int) {
        return recordService.deleteById(id)
    }

    @get:GetMapping("/records")
    @get:PreAuthorize("isAuthenticated()")
    val records: List<DecisionRecord>
        get() = recordService.records

    @GetMapping("/records/{id}")
    @PreAuthorize("isAuthenticated()")
    fun findRecordById(@PathVariable("id") id: Int): DecisionRecord {
        return recordService.findById(id)
    }
}
