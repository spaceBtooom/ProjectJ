package com.spring.web.api.backend.order.file;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OrderAttachedFileRepository extends CrudRepository<OrderAttachedFile, Long> {
	Optional<OrderAttachedFile> findByFilecode(String filecode);
	Integer countByOrderId(Integer id);
	List<OrderAttachedFile> findByOrderId(Long id);
}
