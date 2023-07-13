package com.sstixbackend.core.response;

public record UsersLoginResponse(
		String token,
		Integer level
) {
}