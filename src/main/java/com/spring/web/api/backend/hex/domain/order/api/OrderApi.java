package com.spring.web.api.backend.hex.domain.order.api;

import com.spring.web.api.backend.hex.domain.order.Order;
import com.spring.web.api.backend.hex.domain.tag.Tag;

import java.util.UUID;

public interface OrderApi {
	Order save(Order order);
	void addTag(UUID id, Tag tag);

	void deleteById(UUID id);

	void deleteTag(UUID id, UUID tagId);
}
