package com.spring.web.api.backend.hex.orderFile.api.exception;

import java.io.IOException;

public class OrderFileNoSuchFileOnDiskException extends OrderFileException {
	public OrderFileNoSuchFileOnDiskException() {
	}

	public OrderFileNoSuchFileOnDiskException(String message) {
		super(message);
	}

}
