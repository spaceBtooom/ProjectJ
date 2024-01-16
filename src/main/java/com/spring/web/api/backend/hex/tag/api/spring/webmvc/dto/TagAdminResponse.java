package com.spring.web.api.backend.hex.tag.api.spring.webmvc.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public record TagAdminResponse(@Schema(example = "f5e7ded8-c281-42bf-bfc1-6a5d44ba6765")
			       UUID id,
			       @Schema(example = "Java")
			       String name,
			       @Schema(example = "LANGUAGE")
			       String aliasName) {
}
