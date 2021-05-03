package com.fantastictrio.cw4sem.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    //TODO: Specify more safe roles
    USER(Set.of(Permission.MANAGE_ORGANIZATION, Permission.MANAGE_DECISION)),
    ADMIN(Set.of(Permission.MANAGE_USERS,
            Permission.MANAGE_DECISION,
            Permission.MANAGE_ORGANIZATION,
            Permission.INTERACT_ORGANIZATION));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
