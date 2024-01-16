package com.spring.web.api.backend.hex.orderFile.api.spring.webmvc.exception;

import com.spring.web.api.backend.hex.orderFile.api.useCase.OrderFilecodeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OrderFileExceptionHandler {

	@ExceptionHandler(OrderFilecodeNotFoundException.class)
	ResponseEntity<String> handleOrderFilecodeNotFoundException(OrderFilecodeNotFoundException ex){
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.EXPECTATION_FAILED);
	}
}
