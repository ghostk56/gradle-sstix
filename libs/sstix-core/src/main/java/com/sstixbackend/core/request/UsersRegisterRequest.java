package com.sstixbackend.core.request;

public record UsersRegisterRequest(
        String userName,
        String email,
        String phone,
        String password
) {
}