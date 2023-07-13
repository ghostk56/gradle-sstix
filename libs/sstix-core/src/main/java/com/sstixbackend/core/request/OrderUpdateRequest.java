package com.sstixbackend.core.request;

public record OrderUpdateRequest(
		Integer orderId,
		Integer status
) {
}