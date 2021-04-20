package com.fantastictrio.cw4sem.controller;

import com.fantastictrio.cw4sem.dto.AuthRequest;
import com.fantastictrio.cw4sem.dto.AuthenticationResponse;
import com.fantastictrio.cw4sem.exception.DuplicateUserException;
import com.fantastictrio.cw4sem.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public AuthenticationResponse signup(@RequestBody AuthRequest authRequest) throws DuplicateUserException {
        return authService.signup(authRequest);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody AuthRequest authRequest) {
        return authService.login(authRequest);
    }
}
