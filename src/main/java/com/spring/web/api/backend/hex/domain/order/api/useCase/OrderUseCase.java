package com.spring.web.api.backend.hex.domain.order.api.useCase;

import com.spring.web.api.backend.hex.domain.order.Order;
import com.spring.web.api.backend.hex.domain.tag.Tag;
import com.spring.web.api.backend.hex.domain.order.api.OrderApi;
import com.spring.web.api.backend.hex.domain.order.spi.OrderSpi;

import java.util.UUID;

public class OrderUseCase implements OrderApi {
	private final OrderSpi orderSpi;

	public OrderUseCase(OrderSpi orderSpi) {
		this.orderSpi = orderSpi;
	}

	@Override
	public Order save(final Order order) {
		this.orderSpi.save(order);
		return this.orderSpi
			.findById(order.getId());
	}

	@Override
	public void addTag(UUID id, Tag tag) {
		Order order = this.orderSpi
			.findById(id);
		order.addTag(tag);
		this.orderSpi.save(order);
	}

	@Override
	public void deleteById(UUID id) {
		this.orderSpi.delete(id);
	}

	@Override
	public void deleteTag(UUID id, UUID tagId) {
		Order order = this.orderSpi.findById(id);
		order.removeByTagId(tagId);
		this.orderSpi.save(order);
	}
}
