package com.zinu.inventory.authentication;

public record RegisterReq(
    String firstName,
    String lastName,
    String email,
    String password,
    int tenantId,
    String role
) {
}
