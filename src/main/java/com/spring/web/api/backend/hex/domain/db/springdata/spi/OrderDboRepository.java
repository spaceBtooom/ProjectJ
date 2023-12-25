package com.spring.web.api.backend.hex.domain.db.springdata.spi;

import com.spring.web.api.backend.hex.domain.db.springdata.mapper.OrderEntityMapper;
import com.spring.web.api.backend.hex.domain.order.Order;
import com.spring.web.api.backend.hex.domain.order.spi.OrderSpi;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderDboRepository implements OrderSpi {
	private final SpringDataOrderRepository orderRepository;
	private final OrderEntityMapper orderMapper;

	public OrderDboRepository(SpringDataOrderRepository orderRepository, OrderEntityMapper orderMapper) {
		this.orderRepository = orderRepository;
		this.orderMapper = orderMapper;
	}


	@Override
	public Order findById(UUID id) {
		return orderMapper
			.toDomain(orderRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Unable to find order id db")));
	}

	@Override
	public List<Order> findAll() {
		List<Order> orders = new ArrayList<>();
		orderRepository.findAll().forEach(orderEntity -> orders.add(orderMapper.toDomain(orderEntity)));
		return orders;
	}

	@Override
	public void save(Order order) {
		orderRepository.save(orderMapper.toDbo(order));
	}

	@Override
	public void delete(UUID id) {
		orderRepository.deleteById(id);
	}
}
