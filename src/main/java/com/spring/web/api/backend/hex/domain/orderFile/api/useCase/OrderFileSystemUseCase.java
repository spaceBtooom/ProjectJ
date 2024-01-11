package com.spring.web.api.backend.hex.domain.orderFile.api.useCase;

import com.spring.web.api.backend.hex.domain.order.spi.OrderSpi;
import com.spring.web.api.backend.hex.domain.orderFile.OrderFile;
import com.spring.web.api.backend.hex.domain.orderFile.api.OrderFileApi;
import com.spring.web.api.backend.hex.domain.orderFile.spi.OrderFileSpi;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
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
	public Optional<OrderFile> fileUpload(UUID orderId, MultipartFile file) throws IOException {
		if (!orderSpi.existsById(orderId)) {
			log.error(this.getClass().getName()
				+ ": "
				+ "fileUpload:"
				+ "there is no order with id :"
				+ orderId
			);
			return null;
		}

		String filecode = RandomStringUtils.randomAlphabetic(8);
		Path uploadPath = Paths.get(FILESYSTEM_DIR_NAME).resolve(orderId.toString()).resolve(filecode);
		if(!Files.exists(uploadPath)){
			Files.createDirectories(uploadPath);
		}

		String originalFilename = file.getOriginalFilename();
		String fileName = StringUtils.cleanPath(originalFilename);
		long size = file.getSize();

		try (InputStream inputStream = file.getInputStream()) {
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException ioe) {
			throw new IOException("Could not save file: " + fileName, ioe);
		}

		return orderFileSpi.save(new OrderFile(filecode, fileName, orderId));
	}

	@Override
	public Resource getFileAsResource(UUID orderId, String filecode) throws IOException{
		if(!orderSpi.existsById(orderId)){
			log.error(this.getClass().getName()
				+ ": "
				+ "getFileAsResource:"
				+ "there is no order with id :"
				+ orderId
			);
			return null;
		}
		if(!orderFileSpi.existsByFilecode(filecode)){
			log.error(this.getClass().getName()
				+ ": "
				+ "getFileAsResource:"
				+ "there is no file with filecode :"
				+ filecode
			);
			return null;
		}
		String filename = orderFileSpi.findFilenameByFilecode(filecode);
		Path dirPath = Paths.get(FILESYSTEM_DIR_NAME).resolve(orderId.toString()).resolve(filecode);
		AtomicReference<Path> foundFile = new AtomicReference<>();

		Files.list(dirPath).forEach(file->{
			if(file.getFileName().toString().startsWith(filename)){
				foundFile.set(file);
			}
		});
		if(foundFile.get() != null){
			return new UrlResource(foundFile.get().toUri());
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
}
