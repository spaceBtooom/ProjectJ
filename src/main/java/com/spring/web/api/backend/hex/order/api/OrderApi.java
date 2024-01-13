package com.spring.web.api.backend.hex.order.api;

import com.spring.web.api.backend.hex.order.domain.Order;
import com.spring.web.api.backend.hex.order.api.exeptions.OrderTagNoAvailableException;
import com.spring.web.api.backend.hex.tag.domain.Tag;

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
