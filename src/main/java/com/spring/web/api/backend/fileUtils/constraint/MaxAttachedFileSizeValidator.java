package com.spring.web.api.backend.fileUtils.constraint;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Log4j2
public class MaxAttachedFileSizeValidator implements ConstraintValidator<MaxAttachedFileSizeConstraint, List<MultipartFile>> {

	@Override
	public boolean isValid(List<MultipartFile> multipartFileList, ConstraintValidatorContext constraintValidatorContext) {
		log.info("MultipartFile list size {}", multipartFileList.size());
		return multipartFileList.size() <= 3;
	}
}
