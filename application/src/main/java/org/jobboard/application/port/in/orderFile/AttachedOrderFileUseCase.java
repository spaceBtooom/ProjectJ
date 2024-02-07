package org.jobboard.application.port.in.orderFile;

import org.jobboard.application.service.order.exeptions.OrderException;
import org.jobboard.application.service.orderFile.exception.OrderFileException;
import org.jobboard.domain.attachedFile.AttachedOrderFile;

import java.io.InputStream;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AttachedOrderFileUseCase {
	public Optional<AttachedOrderFile> fileUpload(UUID orderId, String fileName, InputStream fileStream) throws
			OrderFileException, OrderException;

	public URI getFileAsResource(UUID orderId, String fileName) throws OrderException,OrderFileException;

	List<AttachedOrderFile> findByOrderId(UUID id);

	Integer countByOrderId(UUID id);

	boolean existsByOrderId(UUID id);

	long deleteByOrderIdAndfilecode(UUID id, String filecode) throws OrderException, OrderFileException;

	long deleteByOrderId(UUID id) throws OrderFileException, OrderException;
}
