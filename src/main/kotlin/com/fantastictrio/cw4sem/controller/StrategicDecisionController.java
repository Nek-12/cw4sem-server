package com.fantastictrio.cw4sem.controller;

import com.fantastictrio.cw4sem.dto.StrategicDecisionPayload;
import com.fantastictrio.cw4sem.model.StrategicDecision;
import com.fantastictrio.cw4sem.service.StrategicDecisionService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/strategic/decisions")
public class StrategicDecisionController {
    private final StrategicDecisionService service;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<StrategicDecision> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public StrategicDecision findById(@PathVariable("id") int id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public void deleteById(@PathVariable("id") int id) {
        service.deleteById(id);
    }

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public StrategicDecision add(@Valid @RequestBody StrategicDecisionPayload payload) {
        return service.add(payload);
    }
}
