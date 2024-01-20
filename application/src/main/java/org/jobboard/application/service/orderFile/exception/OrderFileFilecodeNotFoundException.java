package org.jobboard.application.service.orderFile.exception;

public class OrderFileFilecodeNotFoundException extends OrderFileException {
	public OrderFileFilecodeNotFoundException() {
	}

	public OrderFileFilecodeNotFoundException(String filecode) {
		super("There is no such filecode as:" + filecode);
	}
}
