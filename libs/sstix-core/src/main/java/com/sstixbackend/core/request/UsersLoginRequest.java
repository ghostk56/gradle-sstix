package com.sstixbackend.core.request;

public record UsersLoginRequest(
        String userName,
        String password
) {
}