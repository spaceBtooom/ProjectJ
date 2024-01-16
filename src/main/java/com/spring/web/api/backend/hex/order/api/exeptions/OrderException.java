package com.spring.web.api.backend.hex.order.api.exeptions;

public class OrderException extends RuntimeException {
	public OrderException() {
	}

	public OrderException(String message) {
		super(message);
	}
}
