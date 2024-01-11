package com.spring.web.api.backend.hex.domain.order.api.exeptions;

import java.time.OffsetDateTime;

public class OrderExpireTimeNotCorrectException extends OrderException {
	final OffsetDateTime createdAt;
	final OffsetDateTime expiredAt;

	public OrderExpireTimeNotCorrectException(OffsetDateTime createdAt, OffsetDateTime expiredAt) {
		this.createdAt = createdAt;
		this.expiredAt = expiredAt;
	}

	@Override
	public String getMessage() {
		return "created at" + createdAt
			+ "expired at" + expiredAt;
	}

}
