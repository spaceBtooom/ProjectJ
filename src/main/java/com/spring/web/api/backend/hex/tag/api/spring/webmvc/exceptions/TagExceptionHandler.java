package com.spring.web.api.backend.hex.tag.api.spring.webmvc.exceptions;

import com.spring.web.api.backend.hex.tag.domain.TagAliasException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TagExceptionHandler {
	@ExceptionHandler(TagAliasException.class)
	public ResponseEntity<String> handleTagAliasException(TagAliasException exception){
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
	}
}
