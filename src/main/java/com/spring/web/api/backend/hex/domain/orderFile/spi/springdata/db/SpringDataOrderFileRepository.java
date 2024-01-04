package com.spring.web.api.backend.hex.domain.orderFile.spi.springdata.db;

import com.spring.web.api.backend.hex.domain.order.Order;
import com.spring.web.api.backend.hex.domain.orderFile.spi.springdata.dbo.OrderFileEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface SpringDataOrderFileRepository extends CrudRepository<OrderFileEntity, UUID> {
	Integer countByOrderId(UUID id);
	List<OrderFileEntity> findByOrderId(UUID id);
	boolean existsByFilecode(String filecode);
	boolean existsByOrderId(UUID id);
}
