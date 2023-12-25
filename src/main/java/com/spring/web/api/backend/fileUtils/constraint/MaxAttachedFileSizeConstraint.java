package com.spring.web.api.backend.fileUtils.constraint;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = MaxAttachedFileSizeValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface MaxAttachedFileSizeConstraint {
	String message() default "The input list cannot contain more than 3 attached files";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
