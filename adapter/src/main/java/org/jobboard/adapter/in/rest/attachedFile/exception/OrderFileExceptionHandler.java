package org.jobboard.adapter.in.rest.attachedFile.exception;

import org.jobboard.application.service.orderFile.exception.OrderFileFilecodeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OrderFileExceptionHandler {

	@ExceptionHandler(OrderFileFilecodeNotFoundException.class)
	ResponseEntity<String> handleOrderfilecodeNotFoundException(OrderFileFilecodeNotFoundException ex){
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.EXPECTATION_FAILED);
	}
}
