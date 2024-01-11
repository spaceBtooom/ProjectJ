package com.spring.web.api.backend.hex.domain.order.api;

import com.spring.web.api.backend.hex.domain.order.Order;
import com.spring.web.api.backend.hex.domain.order.api.exeptions.OrderTagNoAvailableException;
import com.spring.web.api.backend.hex.domain.tag.Tag;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderApi {
	Optional<Order> save(Order order) throws OrderTagNoAvailableException;

	Optional<Order> findById(UUID id);

	List<Order> findAll();

	List<Tag> addTags(UUID id, List<Tag> tag);

	void deleteById(UUID id);

	void deleteTag(UUID id, UUID tagId);
}
