package org.jobboard.adapter.in.rest.common;

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
public class ConstraintViolationExceptionHandler {

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<String> handle(ConstraintViolationException constraintViolationException) {
		Set<ConstraintViolation<?>> violations = constraintViolationException.getConstraintViolations();
		String errorMessage = "";
		if (!violations.isEmpty()) {
			List<String> builder = new ArrayList<>();
			violations.forEach(violation -> builder.add(violation.getMessage()));
			builder.sort(Comparator.reverseOrder());
			errorMessage = builder.stream().reduce("",(partialResult, next)->partialResult.concat(next+"\n"));
		} else {
			errorMessage = "ConstraintViolationException occured.";
		}

		log.error(errorMessage);
		return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
	}

}
