package com.spring.web.api.backend.hex.order.api;

import com.spring.web.api.backend.hex.order.api.exeptions.OrderException;
import com.spring.web.api.backend.hex.order.domain.Order;
import com.spring.web.api.backend.hex.order.api.exeptions.OrderTagNoAvailableException;
import com.spring.web.api.backend.hex.tag.domain.Tag;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderApi {
	Optional<Order> save(Order order) throws OrderException;

	Optional<Order> findById(UUID id);

	List<Order> findAll();

	List<Tag> addTags(UUID id, List<Tag> tag) throws OrderException;

	void deleteById(UUID id) throws OrderException;

	void deleteTag(UUID id, UUID tagId) throws OrderException;
	boolean existsById(UUID id);
}
