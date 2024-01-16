package com.spring.web.api.backend.hex.order;

import com.spring.web.api.backend.hex.order.domain.Order;
import com.spring.web.api.backend.hex.tag.domain.Tag;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public class OrderProvider {
	public static Order getCreatedOrder(){
		return new Order(
			UUID.randomUUID(),
			List.of(new Tag(
				UUID.randomUUID(),
				"Java",
				"LANGUAGE")),
			null,
			"Java",
			"Created order",
			1000,
			"//url",
			OffsetDateTime.now(),
			null,
			OffsetDateTime.MAX
		);
	}
}
