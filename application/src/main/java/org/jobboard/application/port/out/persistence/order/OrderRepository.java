package org.jobboard.application.port.out.persistence.order;

import org.jobboard.domain.order.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {
	Optional<Order> findById(UUID id);
	List<Order> findAll();

	void save(Order order);
	void delete(UUID id);

	boolean existsById(UUID id);

	void update(Order order);
}
