package com.fantastictrio.cw4sem.controller;

import com.fantastictrio.cw4sem.dto.StatisticDecisionPayload;
import com.fantastictrio.cw4sem.model.StatisticDecision;
import com.fantastictrio.cw4sem.service.StatisticDecisionService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/statistic/decisions")
public class StatisticDecisionController {
    private final StatisticDecisionService service;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<StatisticDecision> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public StatisticDecision findById(@PathVariable("id") int id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public void deleteById(@PathVariable("id") int id) {
        service.deleteById(id);
    }

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public StatisticDecision add(@Valid @RequestBody StatisticDecisionPayload payload) {
        return service.add(payload);
    }

    @PostMapping("/update")
    @PreAuthorize("isAuthenticated()")
    public StatisticDecision update(@Valid @RequestBody StatisticDecision decision) {
        return service.update(decision);
    }
}
