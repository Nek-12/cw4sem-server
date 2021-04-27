package com.fantastictrio.cw4sem.model;

public enum Permission {
    MANAGE_USERS("USERS:MANAGE"),
    ADD_ORGANIZATION("ORGANIZATION:ADD");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
