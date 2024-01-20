package org.jobboard.adapter.out.persistence.jpa.tag.exception;

public class TagEntityCannotBeUpdatedException extends TagEntityException {
	public TagEntityCannotBeUpdatedException() {
	}

	public TagEntityCannotBeUpdatedException(String message) {
		super(message);
	}
}
