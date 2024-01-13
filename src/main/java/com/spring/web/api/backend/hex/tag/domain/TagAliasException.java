package com.spring.web.api.backend.hex.tag.domain;

import com.spring.web.api.backend.hex.shared.DomainException;

public class TagAliasException extends DomainException {
	public TagAliasException() {
	}

	public TagAliasException(String message) {
		super(message);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
