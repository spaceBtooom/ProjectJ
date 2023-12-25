package com.spring.web.api.backend.hex.domain.db.springdata.spi;

import com.spring.web.api.backend.hex.domain.db.springdata.dbo.OrderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpringDataOrderRepository extends CrudRepository<OrderEntity, UUID> {
}
