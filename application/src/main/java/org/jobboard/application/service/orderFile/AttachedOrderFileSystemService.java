package org.jobboard.application.service.orderFile;


import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.jobboard.application.port.in.order.OrderUseCase;
import org.jobboard.application.port.in.orderFile.AttachedOrderFileUseCase;
import org.jobboard.application.port.out.persistence.attachedOderFile.AttachedOrderFileRepository;
import org.jobboard.application.service.order.exeptions.OrderException;
import org.jobboard.application.service.order.exeptions.OrderIdNotFoundException;
import org.jobboard.application.service.orderFile.exception.*;
import org.jobboard.domain.attachedFile.AttachedOrderFile;


import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;

public class AttachedOrderFileSystemService implements AttachedOrderFileUseCase {
	private static final String FILESYSTEM_DIR_NAME = "OrderFS";
	private final Logger log = Logger.getLogger(this.getClass().toString());

	private final AttachedOrderFileRepository attachedOrderFileRepository;
	private final OrderUseCase orderUseCase;

	public AttachedOrderFileSystemService(AttachedOrderFileRepository attachedOrderFileRepository, OrderUseCase orderUseCase) {
		this.attachedOrderFileRepository = attachedOrderFileRepository;
		this.orderUseCase = orderUseCase;
	}

	@Override
	public Optional<AttachedOrderFile> fileUpload(UUID orderId, String filename, InputStream fileStream) throws
		OrderException,
		OrderFileException {
		if (!orderUseCase.existsById(orderId)) {
			throw new OrderIdNotFoundException();
		}

		String filecode = RandomStringUtils.randomAlphabetic(8);
		Path uploadPath = Paths.get(FILESYSTEM_DIR_NAME).resolve(orderId.toString()).resolve(filecode);
		if (!Files.exists(uploadPath)) {
			try {
				Files.createDirectories(uploadPath);
			} catch (IOException e) {
				log.info(e.toString());
				throw new OrderFileCannotCreateDirectoryOnDiskException(uploadPath.toString());
			}
		}

		try (fileStream) {
			Path filePath = uploadPath.resolve(filename);
			Files.copy(fileStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			log.info(e.toString());
			throw new OrderFileCannotBeSavedOnDiskException(orderId.toString() + " - " + filename);
		}

		AttachedOrderFile attacheFile = new AttachedOrderFile(UUID.randomUUID());
		attacheFile.setFilecode(filecode);
		attacheFile.setFilename(filename);
		attacheFile.setOrderId(orderId);

		return attachedOrderFileRepository.save(attacheFile);
	}

	@Override
	public URI getFileAsResource(UUID orderId, String filecode) throws
		OrderException,
		OrderFileException {
		if (!orderUseCase.existsById(orderId)) {
			throw new OrderIdNotFoundException(orderId);
		}
		if (attachedOrderFileRepository.existsByFilecode(filecode)) {
			throw new OrderFileFilecodeNotFoundException(filecode);
		}

		String filename = attachedOrderFileRepository.findFileNameByFilecode(filecode);
		Path dirPath = Paths.get(FILESYSTEM_DIR_NAME).resolve(orderId.toString()).resolve(filecode);
		AtomicReference<Path> foundFile = new AtomicReference<>();

		try {
			Files.list(dirPath).forEach(file -> {
				if (file.getFileName().toString().startsWith(filename)) {
					foundFile.set(file);
				}
			});
		} catch (IOException e) {
			log.info(e.toString());
			throw new OrderFileNoSuchFileOnDiskException(e.toString());
		}
		if (foundFile.get() == null) {
			return null;
		}

		return foundFile.get().toUri();
	}

	@Override
	public List<AttachedOrderFile> findByOrderId(UUID id) {
		return attachedOrderFileRepository.findByOrderId(id);
	}

	@Override
	public Integer countByOrderId(UUID id) {
		return attachedOrderFileRepository.countByOrderId(id);
	}

	@Override
	public boolean existsByOrderId(UUID id) {
		return attachedOrderFileRepository.existsByOrderId(id);
	}


	@Override
	public long deleteByOrderIdAndfilecode(UUID id, String filecode) throws
		OrderException,
		OrderFileException {
		if (!orderUseCase.existsById(id)) {
			throw new OrderIdNotFoundException(id);
		}
		if (attachedOrderFileRepository.existsByFilecode(filecode)) {
			throw new OrderFileFilecodeNotFoundException(filecode);
		}
		Path deletePath = Paths.get(FILESYSTEM_DIR_NAME).resolve(id.toString()).resolve(filecode);

		try {
			FileUtils.deleteDirectory(deletePath.toFile());

		} catch (IOException e) {
			log.info(e.toString());
			throw new OrderFileCannotBeDeletedFileOnDiskException();
		}

		return attachedOrderFileRepository.deleteByOrderIdAndFilecode(id, filecode);
	}


	@Override
	public long deleteByOrderId(UUID id) throws OrderException, OrderFileException {
		if (!orderUseCase.existsById(id)) {
			throw new OrderIdNotFoundException(id);
		}
		Path deletePath = Paths.get(FILESYSTEM_DIR_NAME).resolve(id.toString());
		deletePath.toFile().deleteOnExit();
		try {
			FileUtils.deleteDirectory(deletePath.toFile());
		} catch (IOException e) {
			log.info(e.toString());
			throw new OrderFileCannotBeDeletedFileOnDiskException();
		}
		return attachedOrderFileRepository.deleteByOrderId(id);
	}
}
