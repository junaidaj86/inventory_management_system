package com.zinu.inventory.authentication;

public enum Role {
    ROLE_USER,
    ROLE_ADMIN,
    ROLE_SUPERADMIN;

    public static Role fromString(String role) {
        return switch (role.toUpperCase()) {
            case "USER" -> ROLE_USER;
            case "ADMIN" -> ROLE_ADMIN;
            default -> throw new IllegalArgumentException("Unknown role: " + role);
        };
    }
}
