package com.fantastictrio.cw4sem.service;

import com.fantastictrio.cw4sem.dto.AuthenticationResponse;
import com.fantastictrio.cw4sem.dto.LoginRequest;
import com.fantastictrio.cw4sem.dto.UserPayload;
import com.fantastictrio.cw4sem.exception.DuplicateException;
import com.fantastictrio.cw4sem.model.Role;
import com.fantastictrio.cw4sem.model.User;
import com.fantastictrio.cw4sem.repository.UserRepository;
import com.fantastictrio.cw4sem.security.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse signup(UserPayload request) throws DuplicateException {
        if (isUserRegistered(request.getUsername())){
            throw new DuplicateException("User with this username already exists");
        }
        User user = User.builder()
                .password(passwordEncoder.encode(request.getPassword()))
                .username(request.getUsername())
                .role(Role.USER)
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .build();
        userRepository.save(user);

        return generateAuthenticationToken(request.getUsername(), user.getRole());
    }

    public boolean isUserRegistered(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        User user = userRepository.findByUsername(loginRequest.getUsername()).get();
        Role role = user.getRole();
        return generateAuthenticationToken(user.getUsername(), role);
    }

    public AuthenticationResponse generateAuthenticationToken(String username, Role role) {
        String token = jwtTokenProvider.createToken(username);
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .username(username)
                .role(role)
                .build();
    }
}
