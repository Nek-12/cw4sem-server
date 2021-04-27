package com.fantastictrio.cw4sem.controller;

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
    @PreAuthorize("hasAuthority('USERS:MANAGE')")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USERS:MANAGE')")
    public User getUserById(@PathVariable("id") Integer id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USERS:MANAGE')")
    public boolean deleteUserById(@PathVariable("id") Integer id) {
        return userService.deleteUserById(id);
    }

    @GetMapping("/projection")
    public List<User.UserProjection> findAllProjections() {
        return userService.findAllProjections();
    }
}
