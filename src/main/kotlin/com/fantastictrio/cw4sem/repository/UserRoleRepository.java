package com.fantastictrio.cw4sem.repository;

import com.fantastictrio.cw4sem.model.Role;
import com.fantastictrio.cw4sem.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    Optional<UserRole> findByRole(Role role);
}
