package com.spring.web.api.backend.hex.domain.orderFile.spi;

import com.spring.web.api.backend.hex.domain.orderFile.OrderFile;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderFileSpi {
	public Optional<OrderFile> save(OrderFile file);
	public List<OrderFile> findByOrderId(UUID id);

	Integer countByOrderId(UUID id);

	boolean existsByOrderId(UUID id);

	boolean existsByFilecode(String filecode);
}
