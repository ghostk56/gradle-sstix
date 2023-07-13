package com.sstixbackend.core.response;

public record UsersInfoResponse(
		String userName,
		String email,
		String phone
) {
}