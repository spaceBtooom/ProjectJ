package org.jobboard.adapter.out.persistence.jpa.tag.exception;

import org.jobboard.domain.tag.Tag;

public class TagEntityCannotBeSavedException extends TagEntityException {
	final Tag tag;
	public TagEntityCannotBeSavedException(Tag tag) {
		this.tag = tag;
	}

	@Override
	public String getMessage() {
		return tag.toString() +"\n"
			+"cannot be save";
	}
}
