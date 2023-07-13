package com.sstixbackend.core.response;

public record RestfulResponse<T> (
		String returnCode,
		String returnMsg,
		T data
) {
}

