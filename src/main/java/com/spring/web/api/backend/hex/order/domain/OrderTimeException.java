package com.spring.web.api.backend.hex.order.domain;

import com.spring.web.api.backend.hex.shared.DomainException;

import java.time.OffsetDateTime;

public class OrderTimeException extends DomainException {
	public OrderTimeException() {
	}

	public OrderTimeException(String message) {
		super(message);
	}

}
