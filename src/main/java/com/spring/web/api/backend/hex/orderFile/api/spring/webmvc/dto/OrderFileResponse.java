package com.spring.web.api.backend.hex.orderFile.api.spring.webmvc.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record OrderFileResponse(
	@Schema(example = "input.txt")
	String filename,
	@Schema(example = "OrderFS/input.txt")
	String downloadUrl) {
}
