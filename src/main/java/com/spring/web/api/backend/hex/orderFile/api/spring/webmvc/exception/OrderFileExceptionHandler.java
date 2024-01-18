package com.spring.web.api.backend.hex.orderFile.api.spring.webmvc.exception;

import com.spring.web.api.backend.hex.orderFile.api.exception.OrderFileFilecodeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OrderFileExceptionHandler {

	@ExceptionHandler(OrderFileFilecodeNotFoundException.class)
	ResponseEntity<String> handleOrderFilecodeNotFoundException(OrderFileFilecodeNotFoundException ex){
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.EXPECTATION_FAILED);
	}
}
