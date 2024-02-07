package org.jobboard.adapter.out.persistence.jpa.order;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaOrderRepository extends CrudRepository<OrderEntity, UUID> {
}
