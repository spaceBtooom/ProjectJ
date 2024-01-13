package com.spring.web.api.backend.hex.orderFile.api.spring.webmvc.constraint;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE_USE)
@Constraint(validatedBy = FileFormatValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface FileFormatConstraint {
	String message() default "The input file format is not correct";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
