package com.spring.web.api.backend.hex.orderFile.api;

import com.spring.web.api.backend.hex.order.api.exeptions.OrderException;
import com.spring.web.api.backend.hex.orderFile.api.exception.*;
import com.spring.web.api.backend.hex.order.api.exeptions.OrderIdNotFoundException;
import com.spring.web.api.backend.hex.orderFile.domain.OrderFile;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderFileApi {
	public Optional<OrderFile> fileUpload(UUID orderId, MultipartFile file) throws OrderException,
		OrderFileException;

	public Resource getFileAsResource(UUID orderId, String filecode) throws OrderException,OrderFileException;

	List<OrderFile> findByOrderId(UUID id);

	Integer countByOrderId(UUID id);

	boolean existsByOrderId(UUID id);

	long deleteByOrderIdAndFilecode(UUID id, String filecode) throws OrderException, OrderFileException;

	long deleteByOrderId(UUID id) throws OrderFileException, OrderException;
}
