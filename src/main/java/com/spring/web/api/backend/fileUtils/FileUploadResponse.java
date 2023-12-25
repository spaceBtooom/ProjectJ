package com.spring.web.api.backend.fileUtils;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileUploadResponse {
	private String fileName;
	private String downloadUri;
	private long size;
	private String errorMessage;
}
