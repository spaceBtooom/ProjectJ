package org.jobboard.application.service.orderFile.exception;

public class OrderFileNoSuchFileOnDiskException extends OrderFileException {
	public OrderFileNoSuchFileOnDiskException() {
	}

	public OrderFileNoSuchFileOnDiskException(String message) {
		super(message);
	}

}
