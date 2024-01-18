package com.spring.web.api.backend.hex.orderFile.api.useCase;

import com.spring.web.api.backend.hex.order.api.exeptions.OrderException;
import com.spring.web.api.backend.hex.order.api.exeptions.OrderIdNotFoundException;
import com.spring.web.api.backend.hex.order.spi.OrderSpi;
import com.spring.web.api.backend.hex.orderFile.api.exception.*;
import com.spring.web.api.backend.hex.orderFile.domain.OrderFile;
import com.spring.web.api.backend.hex.orderFile.api.OrderFileApi;
import com.spring.web.api.backend.hex.orderFile.spi.OrderFileSpi;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Log4j2
public class OrderFileSystemUseCase implements OrderFileApi {

	private static final String FILESYSTEM_DIR_NAME = "OrderFS";
	private final OrderSpi orderSpi;
	private final OrderFileSpi orderFileSpi;

	public OrderFileSystemUseCase(OrderSpi orderSpi, OrderFileSpi orderFileSpi) {
		this.orderSpi = orderSpi;
		this.orderFileSpi = orderFileSpi;
	}

	@Override
	public Optional<OrderFile> fileUpload(UUID orderId, MultipartFile file) throws
		OrderFileCannotCreateDirectoryOnDiskException,
		OrderIdNotFoundException,
		OrderFileCannotBeSavedOnDiskException {

		if (!orderSpi.existsById(orderId)) {
			throw new OrderIdNotFoundException();
		}

		String filecode = RandomStringUtils.randomAlphabetic(8);
		Path uploadPath = Paths.get(FILESYSTEM_DIR_NAME).resolve(orderId.toString()).resolve(filecode);
		if (!Files.exists(uploadPath)) {
			try {
				Files.createDirectories(uploadPath);
			} catch (IOException e) {
				log.error(e.toString());
				throw new OrderFileCannotCreateDirectoryOnDiskException(uploadPath.toString());
			}
		}

		String originalFilename = file.getOriginalFilename();
		String fileName = StringUtils.cleanPath(originalFilename);
		long size = file.getSize();

		try (InputStream inputStream = file.getInputStream()) {
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			log.error(e.toString());
			throw new OrderFileCannotBeSavedOnDiskException(orderId.toString() + " - " + fileName);
		}

		return orderFileSpi.save(new OrderFile(filecode, fileName, orderId));
	}

	@Override
	public Resource getFileAsResource(UUID orderId, String filecode) throws
		OrderException,
		OrderFileException {
		if (!orderSpi.existsById(orderId)) {
			throw new OrderIdNotFoundException(orderId);
		}
		if (!orderFileSpi.existsByFilecode(filecode)) {
			throw new OrderFileFilecodeNotFoundException(filecode);
		}

		String filename = orderFileSpi.findFilenameByFilecode(filecode);
		Path dirPath = Paths.get(FILESYSTEM_DIR_NAME).resolve(orderId.toString()).resolve(filecode);
		AtomicReference<Path> foundFile = new AtomicReference<>();

		try {
			Files.list(dirPath).forEach(file -> {
				if (file.getFileName().toString().startsWith(filename)) {
					foundFile.set(file);
				}
			});
		} catch (IOException e) {
			log.error(e.toString());
			throw new OrderFileNoSuchFileOnDiskException(e.toString());
		}
		if (foundFile.get() != null) {
			try {
				return new UrlResource(foundFile.get().toUri());
			} catch (MalformedURLException e) {
				log.error(e.toString());
				throw new OrderFileNoSuchFileOnDiskException(e.toString());
			}
		}

		return null;
	}

	@Override
	public List<OrderFile> findByOrderId(UUID id) {
		return orderFileSpi.findByOrderId(id);
	}

	@Override
	public Integer countByOrderId(UUID id) {
		return orderFileSpi.countByOrderId(id);
	}

	@Override
	public boolean existsByOrderId(UUID id) {
		return orderFileSpi.existsByOrderId(id);
	}


	@Override
	public long deleteByOrderIdAndFilecode(UUID id, String filecode)
		throws OrderFileFilecodeNotFoundException,
		OrderFileCannotBeDeletedFileOnDiskException,
		OrderIdNotFoundException {
		if (!orderSpi.existsById(id)) {
			throw new OrderIdNotFoundException(id);
		}
		if (!orderFileSpi.existsByFilecode(filecode)) {
			throw new OrderFileFilecodeNotFoundException(filecode);
		}
		Path deletePath = Paths.get(FILESYSTEM_DIR_NAME).resolve(id.toString()).resolve(filecode);

		try {
			FileUtils.deleteDirectory(deletePath.toFile());
		} catch (IOException e) {
			log.error(e.toString());
			throw new OrderFileCannotBeDeletedFileOnDiskException();
		}

		return orderFileSpi.deleteByOrderIdAndFilecode(id, filecode);
	}


	@Override
	public long deleteByOrderId(UUID id) throws OrderException, OrderFileException{
		if (!orderSpi.existsById(id)) {
			throw new OrderIdNotFoundException(id);
		}
		Path deletePath = Paths.get(FILESYSTEM_DIR_NAME).resolve(id.toString());
		deletePath.toFile().deleteOnExit();
        try {
            FileUtils.deleteDirectory(deletePath.toFile());
        } catch (IOException e) {
		log.error(e.toString());
		throw new OrderFileCannotBeDeletedFileOnDiskException();
        }
        return orderFileSpi.deleteByOrderId(id);
	}
}
