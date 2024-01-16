package com.spring.web.api.backend.hex.order.api.exeptions;

import com.spring.web.api.backend.hex.order.api.exeptions.OrderException;

import java.util.UUID;

public class OrderIdNotFoundException extends OrderException {
	public OrderIdNotFoundException() {
	}

	public OrderIdNotFoundException(UUID id) {
		super("There is no such order with id: " + id.toString());
	}
}
