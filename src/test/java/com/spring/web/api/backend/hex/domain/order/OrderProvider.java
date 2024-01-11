package com.spring.web.api.backend.hex.domain.order;

import com.spring.web.api.backend.hex.domain.order.Order;
import com.spring.web.api.backend.hex.domain.tag.Tag;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

public class OrderProvider {
	public static Order getCreatedOrder(){
		return new Order(
			UUID.randomUUID(),
			List.of(new Tag(
				UUID.randomUUID(),
				"Java",
				1)),
			null,
			"Java",
			"Created order",
			1000,
			"//url",
			OffsetDateTime.now(),
			null,
			null
		);
	}
}
