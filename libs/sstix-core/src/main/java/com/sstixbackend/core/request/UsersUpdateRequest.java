package com.sstixbackend.core.request;

public record UsersUpdateRequest(
        String password,
        String oldPassword
) {
}