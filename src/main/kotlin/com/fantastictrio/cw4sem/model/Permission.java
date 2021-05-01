package com.fantastictrio.cw4sem.model;

public enum Permission {
    MANAGE_USERS("USERS:MANAGE"),
    INTERACT_ORGANIZATION("ORGANIZATION:INTERACT"),
    MANAGE_ORGANIZATION("ORGANIZATION:MANAGE");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
