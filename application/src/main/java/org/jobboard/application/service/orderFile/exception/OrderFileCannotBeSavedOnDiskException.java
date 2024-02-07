package org.jobboard.application.service.orderFile.exception;

public class OrderFileCannotBeSavedOnDiskException extends OrderFileException {
	public OrderFileCannotBeSavedOnDiskException() {
	}

	public OrderFileCannotBeSavedOnDiskException(String message) {
		super(message);
	}
}
