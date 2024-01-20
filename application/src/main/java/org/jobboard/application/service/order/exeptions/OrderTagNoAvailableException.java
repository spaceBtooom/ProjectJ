package org.jobboard.application.service.order.exeptions;


import org.jobboard.domain.tag.Tag;

import java.util.List;

public class OrderTagNoAvailableException extends OrderException {
	final List<String> tagList;

	public OrderTagNoAvailableException(final List<String> tagList) {
		this.tagList = tagList;
	}

	@Override
	public String getMessage() {
		StringBuilder sb = new StringBuilder();
		sb.append("These tags are not exists\n");
		tagList.forEach(tag -> sb.append(tag).append('\n'));
		return sb.toString();
	}
}
