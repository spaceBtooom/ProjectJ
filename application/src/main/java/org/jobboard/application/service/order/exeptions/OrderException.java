package org.jobboard.application.service.order.exeptions;

public class OrderException extends Exception {
	public OrderException() {
	}

	public OrderException(String message) {
		super(message);
	}
}
