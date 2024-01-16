package com.spring.web.api.backend.hex.tag.api.spring.webmvc.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record TagResponse(
	@Schema(example = "Java")
	String name,
	@Schema(example = "LANGUAGE")
	String aliasName) {
}
