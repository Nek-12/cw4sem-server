package com.fantastictrio.cw4sem.controller;

import com.fantastictrio.cw4sem.dto.AuthRequest;
import com.fantastictrio.cw4sem.dto.AuthenticationResponse;
import com.fantastictrio.cw4sem.exception.DuplicateUserException;
import com.fantastictrio.cw4sem.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody AuthRequest authRequest) {
        try {
            authService.signup(authRequest);
        } catch (DuplicateUserException e) {
            return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);
        }
        return new ResponseEntity<>("User Registration Successful", OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody AuthRequest authRequest) {
        return authService.login(authRequest);
    }
}
