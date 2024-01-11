package com.spring.web.api.backend.hex.domain.order.api.useCase;

import com.spring.web.api.backend.hex.domain.order.Order;
import com.spring.web.api.backend.hex.domain.order.api.exeptions.OrderTagNoAvailableException;
import com.spring.web.api.backend.hex.domain.tag.Tag;
import com.spring.web.api.backend.hex.domain.order.api.OrderApi;
import com.spring.web.api.backend.hex.domain.order.spi.OrderSpi;
import com.spring.web.api.backend.hex.domain.tag.api.TagApi;
import lombok.extern.log4j.Log4j2;

import java.util.*;

@Log4j2
public class OrderUseCase implements OrderApi {
	private final OrderSpi orderSpi;
	private final TagApi tagApi;

	public OrderUseCase(OrderSpi orderSpi, TagApi tagApi) {
		this.orderSpi = orderSpi;
		this.tagApi = tagApi;
	}

	@Override
	public Optional<Order> save(final Order order) throws OrderTagNoAvailableException {
		List<Tag> unexistsTags= new ArrayList<>();
		order.getTags().forEach(tag -> {
			if(!tagApi.existsByNameAndAliasId(tag.getName(),tag.getAliasId()))
				unexistsTags.add(tag);}
		);
		if(!unexistsTags.isEmpty()){
			throw new OrderTagNoAvailableException(unexistsTags);
		}
		this.orderSpi.save(order);
		return this.orderSpi
			.findById(order.getId());
	}

	@Override
	public Optional<Order> findById(UUID id) {
		return Optional.empty();
	}

	@Override
	public List<Order> findAll() {
		return orderSpi.findAll();
	}

	@Override
	public List<Tag> addTags(UUID id, List<Tag> tags) {
		Optional<Order> foundOrder = this.orderSpi.findById(id);
		if (foundOrder.isEmpty()) {
			return null;
		}
		List<Tag> uniqTags = new ArrayList<>(tags);
		uniqTags.removeIf(tag -> foundOrder
			.get()
			.getTags()
			.stream()
			.anyMatch(tagOrder -> tagOrder.getName().equals(tag.getName())));

		if (tags.isEmpty())
			return null;
		foundOrder.get().addTags(tags);
		orderSpi.update(foundOrder.get());
		return tags;
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
