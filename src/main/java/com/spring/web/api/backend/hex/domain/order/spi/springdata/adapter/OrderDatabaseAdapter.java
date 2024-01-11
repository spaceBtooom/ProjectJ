package com.spring.web.api.backend.hex.domain.order.spi.springdata.adapter;

import com.spring.web.api.backend.hex.domain.order.spi.springdata.db.SpringDataOrderRepository;
import com.spring.web.api.backend.hex.domain.order.spi.springdata.dbo.OrderEntity;
import com.spring.web.api.backend.hex.domain.order.spi.springdata.mapper.GenericMapper.OrderEntityGenericMapper;
import com.spring.web.api.backend.hex.domain.tag.spi.springdata.db.SpringDataTagRepository;
import com.spring.web.api.backend.hex.domain.order.Order;
import com.spring.web.api.backend.hex.domain.order.spi.OrderSpi;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderDatabaseAdapter implements OrderSpi {
	private final SpringDataOrderRepository orderRepository;
	private final SpringDataTagRepository tagRepository;
	private final OrderEntityGenericMapper orderMapper;

	public OrderDatabaseAdapter(SpringDataOrderRepository orderRepository, SpringDataTagRepository tagRepository, OrderEntityGenericMapper orderMapper) {
		this.orderRepository = orderRepository;
		this.tagRepository = tagRepository;
		this.orderMapper = orderMapper;
	}


	@Override
	public Optional<Order> findById(UUID id) {
		return Optional.of(orderMapper
						.toDomain(orderRepository
							.findById(id)
							.orElseThrow(() -> new RuntimeException("Unable to find order id db"))));
	}

	@Override
	public List<Order> findAll() {
		List<Order> orders = new ArrayList<>();
		orderRepository
			.findAll()
			.forEach(orderEntity -> orders
				.add(orderMapper
					.toDomain(orderEntity)));
		return orders;
	}

	@Override
	public void save(Order order) {
		OrderEntity orderEntity = orderMapper.toDbo(order);
		orderEntity.setTags(
			order
				.getTags()
				.stream()
				.map(tag->tagRepository
					.findByName(tag.getName()))
				.toList());
		orderRepository.save(orderEntity);
	}

	@Override
	public void delete(UUID id) {
		orderRepository.deleteById(id);
	}

	@Override
	public boolean existsById(UUID id) {
		return orderRepository.existsById(id);
	}

	@Override
	public void update(Order order) {
		order.updateOrder();
		this.save(order);
	}
}
