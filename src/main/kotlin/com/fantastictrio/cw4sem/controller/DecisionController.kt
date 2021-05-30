package com.fantastictrio.cw4sem.controller

import com.fantastictrio.cw4sem.DecisionMaker
import com.fantastictrio.cw4sem.dto.DecisionPayload
import com.fantastictrio.cw4sem.model.Decision
import com.fantastictrio.cw4sem.model.DecisionRecord
import com.fantastictrio.cw4sem.service.DecisionService
import com.fantastictrio.cw4sem.service.RecordService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.Size

@Validated
@RestController
@RequestMapping("/decisions")
class DecisionController(
    private val decisionService: DecisionService,
    private val recordService: RecordService,
) {
    @get:GetMapping
    @get:PreAuthorize("isAuthenticated()")
    val decisions: List<Decision>
        get() = decisionService.decisions

    @PostMapping("/update/{id}")
    @PreAuthorize("isAuthenticated()")
    fun updateById(@Valid @RequestBody payload: DecisionPayload, @PathVariable id: Int): Decision? {
        return decisionService.update(payload, id)
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    fun findById(@PathVariable("id") id: Int): Decision {
        return decisionService.findById(id)
    }

    @GetMapping("/user/{id}")
    @PreAuthorize("isAuthenticated()")
    fun findByUserId(@PathVariable("id") id: Int): List<Decision> {
        return decisionService.findByUserId(id)
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    fun deleteById(@PathVariable("id") id: Int) {
        return decisionService.deleteById(id)
    }

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    fun add(@Valid @RequestBody payload: DecisionPayload): Decision? {
        return decisionService.add(payload)
    }

    @PostMapping("/make/{id}")
    @PreAuthorize("isAuthenticated()")
    fun make(
        @PathVariable("id") decisionId: Int,
        @Valid @RequestBody @Size(max = 100, min = 1) matrix: List<List<Double>>
    ):
            DecisionRecord {
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
