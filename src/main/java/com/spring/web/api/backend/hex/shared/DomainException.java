package com.spring.web.api.backend.hex.shared;

public class DomainException extends RuntimeException{
	public DomainException() {
	}

	public DomainException(String message) {
		super(message);
	}
}
