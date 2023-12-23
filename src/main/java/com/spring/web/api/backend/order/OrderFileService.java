package com.spring.web.api.backend.order;

import com.spring.web.api.backend.fileUtils.FileService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class OrderFileService implements FileService {
	private static final String SERVICE_NAME="OrderFS";
	@Override
	public String fileUpload(Integer fileIdentifier, String fileName, MultipartFile multipartFile) throws IOException{
		Path uploadPath = Paths.get(SERVICE_NAME).resolve("id"+fileIdentifier);

		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		String fileCode = RandomStringUtils.randomAlphanumeric(8);

		try (InputStream inputStream = multipartFile.getInputStream()) {
			Path filePath = uploadPath.resolve(fileCode+"-"+fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException ioe) {
			throw new IOException("Could not save file: " + fileName, ioe);
		}

		return fileCode;
	}

	@Override
	public Resource getFileAsResource(Integer fileIdentifier,String fileCode) throws IOException {
		Path dirPath = Paths.get(SERVICE_NAME).resolve("id"+fileIdentifier);
		AtomicReference<Path> foundFile = new AtomicReference<>();

		Files.list(dirPath).forEach(file->{
			if(file.getFileName().toString().startsWith(fileCode)){
				foundFile.set(file);
			}
		});
		if(foundFile.get() != null){
			return new UrlResource(foundFile.get().toUri());
		}

		return null;
	}


}
