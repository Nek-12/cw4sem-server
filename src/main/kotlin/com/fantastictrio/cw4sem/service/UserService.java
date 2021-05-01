package com.fantastictrio.cw4sem.service;

import com.fantastictrio.cw4sem.dto.UserPayload;
import com.fantastictrio.cw4sem.exception.NotFoundException;
import com.fantastictrio.cw4sem.model.User;
import com.fantastictrio.cw4sem.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
    }

    public boolean deleteById(Integer id) {
        userRepository.deleteById(id);
        return true;
    }

    public List<User> getByOrganizationId(int organization_id) {
        return userRepository.getByOrganizationId(organization_id);
    }

    public List<User.UserProjection> findAllProjections() {
        return userRepository.findAllProjectionsBy();
    }

    public User getSelf() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("User not found"));
    }

    public User updateSelf(UserPayload userPayload) {
        User user = getSelf();
        update(user, userPayload);
        return userRepository.save(user);
    }

    public User updateById(UserPayload userPayload, Integer id) {
        User user = getById(id);
        update(user, userPayload);
        return userRepository.save(user);
    }

    private void update(User user, UserPayload userPayload) {
        user.setEmail(userPayload.getEmail());
        user.setUsername(userPayload.getUsername());
        user.setPassword(userPayload.getPassword());
        user.setFirstName(userPayload.getFirstName());
        user.setLastName(userPayload.getLastName());
    }
}
