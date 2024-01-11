package com.spring.web.api.backend.hex.domain.order.spi.springdata.mapper.GenericMapper;

import com.spring.web.api.backend.GenericMapperDE;
import com.spring.web.api.backend.hex.domain.order.Order;
import com.spring.web.api.backend.hex.domain.order.spi.springdata.dbo.OrderEntity;
import com.spring.web.api.backend.hex.domain.orderFile.spi.springdata.mapper.GenericMapper.OrderFileEntityGenericMapper;
import com.spring.web.api.backend.hex.domain.tag.spi.springdata.mapper.GenericMapper.TagEntityGenericMapper;
import org.springframework.stereotype.Service;

@Service
public class OrderEntityGenericMapper implements GenericMapperDE<Order, OrderEntity> {

	private final TagEntityGenericMapper tagEntityMapper;
	private final OrderFileEntityGenericMapper orderFileEntityMapper;

	public OrderEntityGenericMapper(TagEntityGenericMapper tagEntityMapper, OrderFileEntityGenericMapper orderFileEntityMapper) {
		this.tagEntityMapper = tagEntityMapper;
		this.orderFileEntityMapper = orderFileEntityMapper;
	}

	@Override
	public OrderEntity toDbo(Order order) {
		return new OrderEntity(order.getId(),
			order.getTitle(),
			order.getComment(),
			order.getPrice(),
			order.getUrlSource(),
			order.getCreateAt(),
			order.getUpdateAt(),
			order.getExpireAt(),
			order.getTags()
				.stream()
				.map(tagEntityMapper::toDbo)
				.toList(),
			order.getFiles()
				.stream()
				.map(orderFileEntityMapper::toDbo)
				.toList()
		);
	}

	@Override
	public Order toDomain(OrderEntity orderEntity) {
		return new Order(
			orderEntity.getId(),
			orderEntity.getTags()
				.stream()
				.map(tagEntityMapper::toDomain)
				.toList(),
			orderEntity.getFiles()
				.stream()
				.map(orderFileEntityMapper::toDomain)
				.toList(),
			orderEntity.getTitle(),
			orderEntity.getComment(),
			orderEntity.getPrice(),
			orderEntity.getUrlSource(),
			orderEntity.getCreateAt(),
			orderEntity.getUpdateAt(),
			orderEntity.getExpireAt());
	}
}
