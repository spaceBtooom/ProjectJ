package com.spring.web.api.backend.hex.domain.orderFile.api.spring.webmvc.constraint;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Log4j2
public class MaxAttachedFileSizeValidator implements ConstraintValidator<MaxAttachedFileSizeConstraint, List<MultipartFile>> {

	@Override
	public boolean isValid(List<MultipartFile> multipartFileList, ConstraintValidatorContext context) {
		log.info("MultipartFile list size {}", multipartFileList.size());
		return multipartFileList.size() <= 3;
	}
}
