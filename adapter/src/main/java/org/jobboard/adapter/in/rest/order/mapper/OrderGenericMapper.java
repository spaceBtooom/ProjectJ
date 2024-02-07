package org.jobboard.adapter.in.rest.order.mapper;

import org.jobboard.adapter.in.rest.common.mappers.GenericMapperRDR;
import org.jobboard.adapter.in.rest.order.dto.OrderRequest;
import org.jobboard.adapter.in.rest.order.dto.OrderResponse;
import org.jobboard.adapter.in.rest.attachedFile.mapper.OrderFileGenericMapper;

import org.jobboard.adapter.in.rest.tag.mapper.TagGenericMapper;
import org.jobboard.domain.order.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class OrderGenericMapper implements GenericMapperRDR<OrderRequest, Order, OrderResponse> {

	private final TagGenericMapper tagMapper;
	private final OrderFileGenericMapper orderFileMapper;


	public OrderGenericMapper(TagGenericMapper tagMapper, OrderFileGenericMapper orderFileMapper) {
		this.tagMapper = tagMapper;
		this.orderFileMapper = orderFileMapper;
	}

	@Override
	public Order toDomain(OrderRequest orderRequest) {
		if (orderRequest == null) {
			return null;
		}

		Order order = new Order(UUID.randomUUID());

		// Entity<String, Tag>("Java", null)
		orderRequest
			.tagNames()
			.ifPresent(list -> {
				list.forEach(order::addTag);
			});
		order.setTitle(orderRequest.title());
		order.setComment(orderRequest.comment());
		order.setPrice(orderRequest.price());
		order.setUrlSource(orderRequest.urlSource());
		order.setExpireAt(orderRequest.expireAt());

		return order;
	}

	@Override
	public OrderResponse toResponse(Order order) {
		if (order == null) {
			return null;
		}
		return new OrderResponse(
			order.getId(),
			order.getTitle(),
			order.getComment(),
			order.getPrice(),
			order.getUrlSource(),
			order.getExpireAt(),
			order.getTags().values()
				.stream()
				.map(tagMapper::toResponse)
				.toList(),
			order.getFiles()
				.values()
				.stream()
				.map(orderFileMapper::toResponse)
				.toList(),
			order.getCreateAt(),
			order.getUpdateAt()
		);
	}

}
