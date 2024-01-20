package org.jobboard.adapter.in.rest.common.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnum, String> {
	private List<String> acceptedValues;
	@Override
	public void initialize(ValueOfEnum annotation) {
		acceptedValues = Stream.of(annotation.enumClass()
				.getEnumConstants())
			.map(Enum::name)
			.collect(Collectors.toList());
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value == null || !acceptedValues.contains(value)){
			context.disableDefaultConstraintViolation();
			context
				.buildConstraintViolationWithTemplate("Enum value: " + value + " is no lay in the enum's pool "+ acceptedValues)
				.addConstraintViolation();
			return false;
		}
		return true;
	}
}
