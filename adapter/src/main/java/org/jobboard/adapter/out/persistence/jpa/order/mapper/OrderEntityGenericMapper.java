package org.jobboard.adapter.out.persistence.jpa.order.mapper;

import org.jobboard.adapter.out.persistence.jpa.attachedFile.mapper.AttachedOrderFileEntityGenericMapper;
import org.jobboard.adapter.out.persistence.jpa.common.mapper.GenericMapperDE;
import org.jobboard.adapter.out.persistence.jpa.order.OrderEntity;
import org.jobboard.adapter.out.persistence.jpa.tag.mapper.TagEntityGenericMapper;
import org.jobboard.domain.order.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderEntityGenericMapper implements GenericMapperDE<Order, OrderEntity> {

	private final TagEntityGenericMapper tagMapper;
	private final AttachedOrderFileEntityGenericMapper fileMapper;

	public OrderEntityGenericMapper(TagEntityGenericMapper tagMapper, AttachedOrderFileEntityGenericMapper fileMapper) {
		this.tagMapper = tagMapper;
		this.fileMapper = fileMapper;
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
			order
				.getTags()
				.values()
				.stream()
				.map(tagMapper::toDbo)
				.toList(),
			order
				.getFiles()
				.values()
				.stream()
				.map(fileMapper::toDbo)
				.toList()
		);
	}

	@Override
	public Order toDomain(OrderEntity orderEntity) {
		Order order = new Order(orderEntity.getId());
		order.addTagsToEmpty(orderEntity.getTags()
			.stream()
			.map(tagMapper::toDomain)
			.toList());
		order.addOrderFilesToEmpty(orderEntity.getFiles()
			.stream()
			.map(fileMapper::toDomain)
			.toList());
		order.setTitle(orderEntity.getTitle());
		order.setComment(orderEntity.getComment());
		order.setPrice(orderEntity.getPrice());
		order.setUrlSource(orderEntity.getUrlSource());
		order.setExpireAt(orderEntity.getExpireAt());
		order.setCreateAt(orderEntity.getCreateAt());
		order.setUpdateAt(orderEntity.getUpdateAt());
		return order;
	}
}
