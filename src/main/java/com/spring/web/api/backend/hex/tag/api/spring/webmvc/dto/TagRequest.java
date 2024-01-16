package com.spring.web.api.backend.hex.tag.api.spring.webmvc.dto;

import com.spring.web.api.backend.hex.shared.annotation.ValueOfEnum;
import com.spring.web.api.backend.hex.tag.domain.Alias;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;


public record TagRequest(
	@NotBlank(message = "The name is required")
	@Schema(example = "Java", requiredMode = Schema.RequiredMode.REQUIRED)
	String name,

	@NotBlank(message = "The alias is required")
	@ValueOfEnum(enumClass = Alias.class)
	@Schema(example = "LANGUAGE", requiredMode = Schema.RequiredMode.REQUIRED)
	String aliasName) {
}
