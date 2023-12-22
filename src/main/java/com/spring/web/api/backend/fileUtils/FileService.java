package com.spring.web.api.backend.fileUtils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
	public String fileUpload(Integer fileIdentifier, String fileName, MultipartFile multipartFile)  throws IOException;
}
