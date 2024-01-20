package org.jobboard.application.service.order;


import org.jobboard.application.port.in.order.OrderUseCase;
import org.jobboard.application.port.out.persistence.order.OrderRepository;
import org.jobboard.application.port.in.tag.TagUseCase;
import org.jobboard.application.service.order.exeptions.OrderException;
import org.jobboard.application.service.order.exeptions.OrderIdNotFoundException;
import org.jobboard.application.service.order.exeptions.OrderTagNoAvailableException;
import org.jobboard.domain.order.Order;
import org.jobboard.domain.tag.Tag;

import java.util.*;

import static org.apache.commons.lang3.BooleanUtils.forEach;

public class OrderService implements OrderUseCase {
	private final OrderRepository orderRepository;
	private final TagUseCase tagUseCase;

	public OrderService(OrderRepository orderRepository, TagUseCase tagUseCase) {
		this.orderRepository = orderRepository;
		this.tagUseCase = tagUseCase;
	}

	@Override
	public Optional<Order> save(final Order order) throws OrderException {

		order.addTagsToEmpty(
			tagsValidate(
				order.getTags()
					.keySet()
					.stream()
					.toList()));

		this.orderRepository.save(order);
		return this.orderRepository
			.findById(order.getId());
	}

	@Override
	public Optional<Order> findById(UUID id) {
		return Optional.empty();
	}

	@Override
	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	@Override
	public List<Tag> addTags(UUID id, List<String> tags) throws OrderException {
		Order foundOrder = this.orderRepository.findById(id)
			.orElseThrow(() -> new OrderIdNotFoundException(id));
		List<Tag> addedTags = tagsValidate(tags);
		foundOrder.addTags(addedTags);
		return addedTags;
	}

	@Override
	public void deleteById(UUID id) throws OrderException {
		if (!orderRepository.existsById(id)) {
			throw new OrderIdNotFoundException(id);
		}
		orderRepository.delete(id);
	}

	@Override
	public void deleteTagByName(UUID id, String tagName) throws OrderException {
		Order order = this.orderRepository.findById(id)
			.orElseThrow(() -> new OrderIdNotFoundException(id));
		order.removeByTagName(tagName);
		this.orderRepository.save(order);

	}

	@Override
	public boolean existsById(UUID id) {
		return orderRepository.existsById(id);
	}

	private List<Tag> tagsValidate(final List<String> tagsNames) throws OrderException {
		List<Tag> normalTags = new ArrayList<>();
		List<String> errorTag = new ArrayList<>();
		tagsNames.forEach(tagName -> {
			Optional<Tag> tag = tagUseCase.findByName(tagName);
			if (tag.isPresent()) {
				normalTags.add(tag.get());
			} else {
				errorTag.add(tagName);
			}
		});
		if (!errorTag.isEmpty()) {
			throw new OrderTagNoAvailableException(errorTag);
		}
		return normalTags;
	}
}
