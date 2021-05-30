package com.fantastictrio.cw4sem.controller;

import com.fantastictrio.cw4sem.dto.AuthenticationResponse;
import com.fantastictrio.cw4sem.dto.LoginRequest;
import com.fantastictrio.cw4sem.dto.UserPayload;
import com.fantastictrio.cw4sem.exception.DuplicateException;
import com.fantastictrio.cw4sem.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public AuthenticationResponse signup(@Valid @RequestBody UserPayload userPayload) throws DuplicateException {
        return authService.signup(userPayload);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }
}
