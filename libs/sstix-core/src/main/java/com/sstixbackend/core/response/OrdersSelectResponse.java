package com.sstixbackend.core.response;

import java.util.Date;

public record OrdersSelectResponse(
		Integer id,
		Integer userId,
		EventsSelectResponse event,
		Integer quantity,
		Integer eventPrice,
		Date orderDate,
        Integer status
) {
}