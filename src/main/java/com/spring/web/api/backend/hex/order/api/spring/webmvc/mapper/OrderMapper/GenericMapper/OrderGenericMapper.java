package com.spring.web.api.backend.hex.order.api.spring.webmvc.mapper.OrderMapper.GenericMapper;

import com.spring.web.api.backend.GenericMapperRDR;
import com.spring.web.api.backend.hex.order.domain.Order;
import com.spring.web.api.backend.hex.order.api.spring.webmvc.dto.OrderRequest;
import com.spring.web.api.backend.hex.order.api.spring.webmvc.dto.OrderResponse;
import com.spring.web.api.backend.hex.orderFile.api.spring.webmvc.mapper.GenericMapper.OrderFileGenericMapper;
import com.spring.web.api.backend.hex.tag.api.spring.webmvc.dto.TagRequest;
import com.spring.web.api.backend.hex.tag.api.spring.webmvc.mapper.GenericMapper.TagGenericMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class OrderGenericMapper implements GenericMapperRDR<OrderRequest, Order, OrderResponse> {

	private final OrderFileGenericMapper orderFileMapper;
	private final TagGenericMapper tagFileMapper;

	public OrderGenericMapper(OrderFileGenericMapper orderFileMapper, TagGenericMapper tagFileMapper) {
		this.orderFileMapper = orderFileMapper;
		this.tagFileMapper = tagFileMapper;
	}

	@Override
	public Order toDomain(OrderRequest orderRequest) {
		if (orderRequest == null) {
			return null;
		}
		return new Order(
			orderRequest.tags()
				.stream()
				.map(tagFileMapper::toDomain)
				.toList(),
			orderRequest.title(),
			orderRequest.comment(),
			orderRequest.price(),
			orderRequest.urlSource(),
			orderRequest.expireAt());
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
			order.getTags()
				.stream()
				.map(tagFileMapper::toResponse)
				.toList(),
			order.getFiles()
				.stream()
				.map(orderFileMapper::toResponse)
				.toList(),
			order.getCreateAt(),
			order.getUpdateAt()
		);
	}

}
