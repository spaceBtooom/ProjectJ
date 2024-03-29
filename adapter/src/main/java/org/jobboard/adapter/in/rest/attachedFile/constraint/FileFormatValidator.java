package org.jobboard.adapter.in.rest.attachedFile.constraint;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileFormatValidator implements ConstraintValidator<FileFormatConstraint, MultipartFile> {
	@Override
	public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {
		Pattern pattern = Pattern.compile("\\.(doc|docx|dot|dotx|rtf|pdf|ppt|pptx|txt|odt)$");
		String fileName = multipartFile.getOriginalFilename();
		Matcher matcher = pattern.matcher(fileName);
		if(!matcher.find()){
			context.disableDefaultConstraintViolation();
			context
				.buildConstraintViolationWithTemplate("File: " + fileName + " format is not supported")
				.addConstraintViolation();
			return false;
		}

		return true;
	}
}
