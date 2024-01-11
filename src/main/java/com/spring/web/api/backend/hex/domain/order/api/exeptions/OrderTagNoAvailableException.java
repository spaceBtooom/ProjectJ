package com.spring.web.api.backend.hex.domain.order.api.exeptions;

import com.spring.web.api.backend.hex.domain.tag.Tag;

import java.util.List;

public class OrderTagNoAvailableException extends OrderException {
	final List<Tag> tagList;

	public OrderTagNoAvailableException(List<Tag> tagList) {
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
