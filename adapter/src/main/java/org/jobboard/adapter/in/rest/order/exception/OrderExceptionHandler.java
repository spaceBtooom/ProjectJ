package org.jobboard.adapter.in.rest.order.exception;

import lombok.extern.log4j.Log4j2;
import org.jobboard.application.service.order.exeptions.OrderIdNotFoundException;
import org.jobboard.application.service.order.exeptions.OrderTagNoAvailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
public class OrderExceptionHandler {

	@ExceptionHandler(OrderTagNoAvailableException.class)
	public ResponseEntity<String> handleOrderTagNoAvailable(OrderTagNoAvailableException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.EXPECTATION_FAILED);
	}

	@ExceptionHandler(OrderIdNotFoundException.class)
	public ResponseEntity<String> handleOrderIdNotFoundException(OrderIdNotFoundException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
	}
}
