package com.spring.web.api.backend.hex.order.spi;

import com.spring.web.api.backend.hex.order.domain.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderSpi {
	Optional<Order> findById(UUID id);
	List<Order> findAll();

	void save(Order order);
	void delete(UUID id);

	boolean existsById(UUID id);

	void update(Order order);
}
