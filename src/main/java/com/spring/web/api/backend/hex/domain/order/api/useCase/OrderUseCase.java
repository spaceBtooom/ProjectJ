package com.spring.web.api.backend.hex.domain.order.api.useCase;

import com.spring.web.api.backend.hex.domain.order.Order;
import com.spring.web.api.backend.hex.domain.tag.Tag;
import com.spring.web.api.backend.hex.domain.order.api.OrderApi;
import com.spring.web.api.backend.hex.domain.order.spi.OrderSpi;
import com.spring.web.api.backend.hex.domain.tag.api.TagApi;
import lombok.extern.log4j.Log4j2;

import java.util.Optional;
import java.util.UUID;

@Log4j2
public class OrderUseCase implements OrderApi {
	private final OrderSpi orderSpi;

	public OrderUseCase(OrderSpi orderSpi) {
		this.orderSpi = orderSpi;
	}

	@Override
	public Optional<Order> save(final Order order) {
		this.orderSpi.save(order);
		return this.orderSpi
			.findById(order.getId());
	}

	@Override
	public void addTag(UUID id, Tag tag) {
		this.orderSpi.findById(id)
			.ifPresentOrElse(order -> {
				order.addTag(tag);
				orderSpi.save(order);
			},()->{
				log.error("addTag in Order: there is no order with id: " + id);
			});
	}

	@Override
	public void deleteById(UUID id) {
		this.orderSpi.delete(id);
	}

	@Override
	public void deleteTag(UUID id, UUID tagId) {
		this.orderSpi.findById(id)
			.ifPresentOrElse(order -> {
				order.removeByTagId(tagId);
				this.orderSpi.save(order);
			}, () -> {
				log.error("deleteTag in Order: there is no order with id: " + id);
			});

	}
}
