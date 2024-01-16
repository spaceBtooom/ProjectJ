package com.spring.web.api.backend.hex.orderFile.api.useCase;

public class OrderFilecodeNotFoundException extends RuntimeException {
	public OrderFilecodeNotFoundException() {
	}

	public OrderFilecodeNotFoundException(String filecode) {
		super("There is no such filecode as:" + filecode);
	}
}
