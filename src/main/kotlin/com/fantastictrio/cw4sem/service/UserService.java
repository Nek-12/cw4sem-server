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

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
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
        user.setPassword(passwordEncoder.encode(userPayload.getPassword()));
        user.setFirstName(userPayload.getFirstName());
        user.setLastName(userPayload.getLastName());
        if (userPayload.getOrganizationId() == null && user.getOrganization() != null) {
            //remove organization
            System.out.println("Remove organization");
            user.setOrganization(null);
        } else if (
                (user.getOrganization() == null && userPayload.getOrganizationId() != null) //new organization
                        || (user.getOrganization().getId() != userPayload.getOrganizationId()) //change organization
        ) {
            System.out.printf("new or change organization: was %s became %s (id) %n",user.getOrganization(),
                    userPayload.getOrganizationId());
            var org = organizationRepository.getById(userPayload.getOrganizationId());
            user.setOrganization(org);
        }
    }

    public User promoteUser(Integer id) {
        User user = getById(id);
        user.setRole(Role.ADMIN);
        return userRepository.save(user);
    }
}
