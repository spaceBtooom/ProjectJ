package com.spring.web.api.backend.hex.domain.orderFile.api;

import com.spring.web.api.backend.hex.domain.orderFile.OrderFile;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderFileApi {
	public Optional<OrderFile> fileUpload(UUID orderId, MultipartFile file) throws IOException;
	public Resource getFileAsResource(UUID orderId, String filecode) throws IOException;

	List<OrderFile> findByOrderId(UUID id);

	Integer countByOrderId(UUID id);
}
