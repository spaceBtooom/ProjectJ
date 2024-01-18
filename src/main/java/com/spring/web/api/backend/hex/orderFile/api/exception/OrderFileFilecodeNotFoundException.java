package com.spring.web.api.backend.hex.orderFile.api.exception;

public class OrderFileFilecodeNotFoundException extends OrderFileException {
	public OrderFileFilecodeNotFoundException() {
	}

	public OrderFileFilecodeNotFoundException(String filecode) {
		super("There is no such filecode as:" + filecode);
	}
}
