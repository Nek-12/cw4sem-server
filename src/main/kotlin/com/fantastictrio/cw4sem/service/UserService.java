package com.fantastictrio.cw4sem.service;

import com.fantastictrio.cw4sem.dto.UserPayload;
import com.fantastictrio.cw4sem.exception.NotFoundException;
import com.fantastictrio.cw4sem.model.Organization;
import com.fantastictrio.cw4sem.model.Role;
import com.fantastictrio.cw4sem.model.User;
import com.fantastictrio.cw4sem.repository.OrganizationRepository;
import com.fantastictrio.cw4sem.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final OrganizationRepository organizationRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    public List<User> findByOrganizationId(int organization_id) {
        return userRepository.findByOrganizationId(organization_id);
    }

    public List<User.UserProjection> findAllProjections() {
        return userRepository.findAllProjectionsBy();
    }

    public User findSelf() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("User not found"));
    }

    public User updateSelf(UserPayload userPayload) {
        User user = findSelf();
        user = update(user, userPayload);
        return userRepository.save(user);
    }

    public User updateById(UserPayload userPayload, Integer id) {
        User user = findById(id);
        user = update(user, userPayload);
        return userRepository.save(user);
    }

    private User update(User user, UserPayload userPayload) {
        var newPassword = "";
        if (!(userPayload.getPassword() == null || userPayload.getPassword().isBlank())) {
            newPassword = passwordEncoder.encode(userPayload.getPassword());
        }
        Organization org;
        if (userPayload.getOrganizationId() != null) {
            org = organizationRepository.findById(userPayload.getOrganizationId()).orElse(user.getOrganization());
        } else {
            org = user.getOrganization();
        }
        return new User(userPayload, newPassword, user.getRole(), org, user.getId(), user.getCreatedDate());
    }

    public User promoteUser(Integer id) {
        User user = findById(id);
        user.setRole(Role.ADMIN);
        return userRepository.save(user);
    }
}
