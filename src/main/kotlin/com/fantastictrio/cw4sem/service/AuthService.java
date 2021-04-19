package com.fantastictrio.cw4sem.service;

import com.fantastictrio.cw4sem.dto.AuthRequest;
import com.fantastictrio.cw4sem.dto.AuthenticationResponse;
import com.fantastictrio.cw4sem.exception.DuplicateUserException;
import com.fantastictrio.cw4sem.model.Role;
import com.fantastictrio.cw4sem.model.Status;
import com.fantastictrio.cw4sem.model.User;
import com.fantastictrio.cw4sem.repository.UserRepository;
import com.fantastictrio.cw4sem.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    public void signup(AuthRequest authRequest) throws DuplicateUserException {
        if (isUserRegistered(authRequest.getUsername())){
            throw new DuplicateUserException("User with this username already exists");
        }
        User user = User.builder()
                .password(passwordEncoder.encode(authRequest.getPassword()))
                .username(authRequest.getUsername())
                .createdDate(Instant.now())
                .status(Status.ACTIVE)
                .role(Role.USER)
                .build();
        userRepository.save(user);
    }

    public boolean isUserRegistered(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public AuthenticationResponse login(AuthRequest authRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        String token = jwtTokenProvider.createToken(authRequest.getUsername());
        return AuthenticationResponse.builder()
            .authenticationToken(token)
            .username(authRequest.getUsername())
            .build();
    }

    public User getUserByName(String username) {
        return userRepository.findByUsername(username).get();
    }
}
