package com.spring.web.api.backend.hex.domain.order.api.spring.webmvc.exception;

import com.spring.web.api.backend.hex.domain.order.api.exeptions.OrderExpireTimeNotCorrectException;
import com.spring.web.api.backend.hex.domain.order.api.exeptions.OrderTagNoAvailableException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@RestControllerAdvice
@Log4j2
public class OrderExceptionHandler {

	@ExceptionHandler(OrderTagNoAvailableException.class)
	public ResponseEntity<String> handleOrderNoAvailable(OrderTagNoAvailableException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(OrderExpireTimeNotCorrectException.class)
	public ResponseEntity<String> handleOrderExpiredTimeNotValid(OrderExpireTimeNotCorrectException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
