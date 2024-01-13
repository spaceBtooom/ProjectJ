package com.spring.web.api.backend.hex.tag.api.spring.webmvc.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record TagRequest(
	@Schema(example = "Java")
	String name,
	@Schema(example = "1")
	Integer aliasId) {
}
