package org.jobboard.adapter.out.persistence.jpa.order;


import org.jobboard.adapter.out.persistence.jpa.order.mapper.OrderEntityGenericMapper;
import org.jobboard.application.port.out.persistence.order.OrderRepository;
import org.jobboard.domain.order.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderAdapter implements OrderRepository {

	private final JpaOrderRepository repository;
	private final OrderEntityGenericMapper orderMapper;

	public OrderAdapter(JpaOrderRepository repository, OrderEntityGenericMapper orderMapper) {
		this.repository = repository;
		this.orderMapper = orderMapper;
	}

	@Override
	public Optional<Order> findById(UUID id) {
		return Optional.of(orderMapper
				.toDomain(repository
					.findById(id)
					.orElseThrow(() -> new RuntimeException("Unable to find order id db"))));
	}

	@Override
	public List<Order> findAll() {
		List<Order> orders = new ArrayList<>();
		repository
			.findAll()
			.forEach(orderEntity -> orders
				.add(orderMapper
					.toDomain(orderEntity)));
		return orders;
	}

	@Override
	public void save(Order order) {
		OrderEntity orderEntity = orderMapper.toDbo(order);
		repository.save(orderEntity);
	}

	@Override
	public void delete(UUID id) {
		repository.deleteById(id);
	}

	@Override
	public boolean existsById(UUID id) {
		return repository.existsById(id);
	}

	@Override
	public void update(Order order) {
		order.updateOrder();
		this.save(order);
	}
}
