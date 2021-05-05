package com.fantastictrio.cw4sem.controller;

import com.fantastictrio.cw4sem.dto.UserPayload;
import com.fantastictrio.cw4sem.model.User;
import com.fantastictrio.cw4sem.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/self")
    @PreAuthorize("isAuthenticated()")
    public User findSelf() {
        return userService.findSelf();
    }

    @PostMapping("/update/self")
    @PreAuthorize("isAuthenticated()")
    public User updateSelf(@RequestBody UserPayload userPayload) {
        return userService.updateSelf(userPayload);
    }

    @PostMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User updateById(@RequestBody UserPayload userPayload, @PathVariable("id") Integer id) {
        return userService.updateById(userPayload, id);
    }

    @PostMapping("/{id}/promote")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User promoteUser(@PathVariable("id") Integer id) {
        return userService.promoteUser(id);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User findById(@PathVariable("id") Integer id) {
        return userService.findById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteById(@PathVariable("id") Integer id) {
        userService.deleteById(id);
    }

    @GetMapping("/projections")
    public List<User.UserProjection> findAllProjections() {
        return userService.findAllProjections();
    }
}
