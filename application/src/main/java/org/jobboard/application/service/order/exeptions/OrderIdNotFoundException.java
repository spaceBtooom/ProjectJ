package org.jobboard.application.service.order.exeptions;

import java.util.UUID;

public class OrderIdNotFoundException extends OrderException {
	public OrderIdNotFoundException() {
	}

	public OrderIdNotFoundException(UUID id) {
		super("There is no such order with id: " + id.toString());
	}
}
