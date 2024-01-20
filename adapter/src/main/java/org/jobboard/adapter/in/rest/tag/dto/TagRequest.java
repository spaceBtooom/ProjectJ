package org.jobboard.adapter.in.rest.tag.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import org.jobboard.adapter.in.rest.common.annotation.ValueOfEnum;
import org.jobboard.domain.tag.Alias;

import java.util.UUID;


public record TagRequest(
	@NotBlank(message = "The name is required")
	@Schema(example = "Java",
		requiredMode = Schema.RequiredMode.REQUIRED)
	String name,

	@NotBlank(message = "The alias is required")
	@ValueOfEnum(enumClass = Alias.class)
	@Schema(example = "LANGUAGE", requiredMode = Schema.RequiredMode.REQUIRED)
	String alias) {
}
