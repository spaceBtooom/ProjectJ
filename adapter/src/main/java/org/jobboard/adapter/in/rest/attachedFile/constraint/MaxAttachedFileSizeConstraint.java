package org.jobboard.adapter.in.rest.attachedFile.constraint;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = MaxAttachedFileSizeValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface MaxAttachedFileSizeConstraint {
	String message() default "The input files' list cannot contain more than 3 attached files";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
