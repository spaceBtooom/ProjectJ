package org.jobboard.application.port.in.order;



import org.jobboard.application.service.order.exeptions.OrderException;
import org.jobboard.domain.order.Order;
import org.jobboard.domain.tag.Tag;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderUseCase {
	Optional<Order> save(Order order) throws OrderException;

	Optional<Order> findById(UUID id);

	List<Order> findAll();

	List<Tag> addTags(UUID id, List<String> tag) throws OrderException;

	void deleteById(UUID id) throws OrderException;

	void deleteTagByName(UUID id, String name) throws OrderException;
	boolean existsById(UUID id);
}
