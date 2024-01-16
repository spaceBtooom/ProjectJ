package com.spring.web.api.backend.hex.orderFile.spi;

import com.spring.web.api.backend.hex.orderFile.domain.OrderFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderFileSpi {
	public Optional<OrderFile> save(OrderFile file);

	public List<OrderFile> findByOrderId(UUID id);

	Integer countByOrderId(UUID id);

	boolean existsByOrderId(UUID id);

	boolean existsByFilecode(String filecode);

	String findFilenameByFilecode(String filecode);

	long deleteByOrderIdAndFilecode(UUID id, String filecode);

	long deleteByOrderId(UUID id);
}
