package com.fantastictrio.cw4sem.service;

import com.fantastictrio.cw4sem.dto.AuthenticationResponse;
import com.fantastictrio.cw4sem.dto.LoginRequest;
import com.fantastictrio.cw4sem.dto.RegisterRequest;
import com.fantastictrio.cw4sem.exception.DuplicateUserException;
import com.fantastictrio.cw4sem.model.Role;
import com.fantastictrio.cw4sem.model.User;
import com.fantastictrio.cw4sem.model.UserRole;
import com.fantastictrio.cw4sem.repository.UserRepository;
import com.fantastictrio.cw4sem.repository.UserRoleRepository;
import com.fantastictrio.cw4sem.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final UserRole userRole;

    public AuthService(PasswordEncoder passwordEncoder, UserRepository userRepository,
                       JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager,
                       UserRoleRepository userRoleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
        this.userRole = userRoleRepository.findByRole(Role.USER).get();
    }

    public AuthenticationResponse signup(RegisterRequest request) throws DuplicateUserException {
        if (isUserRegistered(request.getUsername())){
            throw new DuplicateUserException("User with this username already exists");
        }
        User user = User.builder()
                .password(passwordEncoder.encode(request.getPassword()))
                .username(request.getUsername())
                .userRole(userRole)
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .build();
        userRepository.save(user);

        return generateAuthenticationToken(request.getUsername());
    }

    public boolean isUserRegistered(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        return generateAuthenticationToken(loginRequest.getUsername());
    }

    public AuthenticationResponse generateAuthenticationToken(String username) {
        String token = jwtTokenProvider.createToken(username);
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .username(username)
                .build();
    }
}
