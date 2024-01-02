package com.spring.web.api.backend.hex.domain.order.api.spring.webmvc.mapper;

import com.spring.web.api.backend.GenericMapper;
import com.spring.web.api.backend.hex.domain.order.Order;
import com.spring.web.api.backend.hex.domain.order.api.spring.webmvc.dto.OrderRequest;
import com.spring.web.api.backend.hex.domain.order.api.spring.webmvc.dto.OrderResponse;
import com.spring.web.api.backend.hex.domain.tag.Tag;
import com.spring.web.api.backend.hex.domain.tag.api.spring.webmvc.dto.TagResponse;

import java.util.Locale;

public class OrderGenericMapper implements GenericMapper<OrderRequest, Order, OrderResponse> {
	@Override
	public Order toDomain(OrderRequest orderRequest) {
		return new Order(orderRequest.tags().stream().map(tagRequest -> new Tag(tagRequest.name(), null)).toList(),
			orderRequest.title(),
			orderRequest.comment(),
			orderRequest.price(),
			orderRequest.urlSource(),
			orderRequest.expireAt());
	}

	@Override
	public OrderResponse toResponse(Order order) {
		return new OrderResponse(order.getId(),
						order.getTitle(),
						order.getComment(),
						order.getPrice(),
			order.getUrlSource(),
			order.getExpireAt(),
			order.getTags().stream().map(tag -> new TagResponse(tag.getName(), tag.getAliasId())).toList(),
			order.getFiles(),
			order.getCreateAt(),
			order.getUpdateAt());

	}
}
