package com.spring.web.api.backend.hex.order.api.spring.webmvc.exception;

import com.spring.web.api.backend.hex.order.api.exeptions.OrderTagNoAvailableException;
import com.spring.web.api.backend.hex.order.domain.OrderTimeException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
public class OrderExceptionHandler {

	@ExceptionHandler(OrderTagNoAvailableException.class)
	public ResponseEntity<String> handleOrderNoAvailable(OrderTagNoAvailableException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(OrderTimeException.class)
	public ResponseEntity<String> handleOrderExpiredTimeNotValid(OrderTimeException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
