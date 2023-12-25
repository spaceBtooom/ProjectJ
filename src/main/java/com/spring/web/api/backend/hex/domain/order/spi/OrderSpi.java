package com.spring.web.api.backend.hex.domain.order.spi;

import com.spring.web.api.backend.hex.domain.order.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderSpi {
	Order findById(UUID id);
	List<Order> findAll();

	void save(Order order);
	void delete(UUID id);

}
