package com.fantastictrio.cw4sem.service;

import com.fantastictrio.cw4sem.exception.NoSuchUserException;
import com.fantastictrio.cw4sem.model.User;
import com.fantastictrio.cw4sem.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new NoSuchUserException("User not found"));
    }

    public boolean deleteUserById(Integer id) {
        userRepository.deleteById(id);
        return true;
    }

    public List<User.UserProjection> findAllProjections() {
        return userRepository.findAllProjectionsBy();
    }
}
