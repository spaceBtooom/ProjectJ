package com.spring.web.api.backend.hex.order.api.useCase;

import com.spring.web.api.backend.hex.order.api.exeptions.OrderException;
import com.spring.web.api.backend.hex.order.api.exeptions.OrderIdNotFoundException;
import com.spring.web.api.backend.hex.order.domain.Order;
import com.spring.web.api.backend.hex.order.api.exeptions.OrderTagNoAvailableException;
import com.spring.web.api.backend.hex.tag.domain.Tag;
import com.spring.web.api.backend.hex.order.api.OrderApi;
import com.spring.web.api.backend.hex.order.spi.OrderSpi;
import com.spring.web.api.backend.hex.tag.api.TagApi;
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
	public Optional<Order> save(final Order order) throws OrderException {
		List<Tag> unexistsTags= new ArrayList<>();
		order.getTags().forEach(tag -> {
			if(!tagApi.existsByNameAndAliasId(tag.getName(),tag.getAliasName()))
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
	public List<Tag> addTags(UUID id, List<Tag> tags) throws OrderException {
		Order foundOrder = this.orderSpi.findById(id)
			.orElseThrow(()-> new OrderIdNotFoundException(id));

		List<Tag> uniqTags = new ArrayList<>(tags);
		uniqTags.removeIf(tag -> foundOrder
			.getTags()
			.stream()
			.anyMatch(tagOrder ->
				tagOrder.getName().equals(tag.getName())));

		if (uniqTags.isEmpty())
			return null;

		foundOrder.addTags(tags);
		orderSpi.update(foundOrder);
		return tags;
	}

	@Override
	public void deleteById(UUID id) throws OrderException {
		if(!orderSpi.existsById(id)){
			throw new OrderIdNotFoundException(id);
		}
		orderSpi.delete(id);
	}

	@Override
	public void deleteTag(UUID id, UUID tagId) throws OrderException {
		Order order = this.orderSpi.findById(id)
			.orElseThrow(()-> new OrderIdNotFoundException(id));
		order.removeByTagId(tagId);
		this.orderSpi.save(order);

	}

	@Override
	public boolean existsById(UUID id) {
		return orderSpi.existsById(id);
	}
}
