package com.spring.web.api.backend.hex.orderFile.api;

import com.spring.web.api.backend.hex.orderFile.api.useCase.OrderFilecodeNotFoundException;
import com.spring.web.api.backend.hex.order.api.exeptions.OrderIdNotFoundException;
import com.spring.web.api.backend.hex.orderFile.domain.OrderFile;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderFileApi {
	public Optional<OrderFile> fileUpload(UUID orderId, MultipartFile file) throws IOException,
		OrderIdNotFoundException;

	public Resource getFileAsResource(UUID orderId, String filecode) throws IOException,
		OrderIdNotFoundException,
		OrderFilecodeNotFoundException;

	List<OrderFile> findByOrderId(UUID id);

	Integer countByOrderId(UUID id);

	boolean existsByOrderId(UUID id);

	long deleteByOrderIdAndFilecode(UUID id, String filecode);

	long deleteByOrderId(UUID id);
}
