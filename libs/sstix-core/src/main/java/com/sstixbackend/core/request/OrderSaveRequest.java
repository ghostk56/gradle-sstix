package com.sstixbackend.core.request;

public record OrderSaveRequest(
		Integer eventsId,
        Integer quantity
) {
}