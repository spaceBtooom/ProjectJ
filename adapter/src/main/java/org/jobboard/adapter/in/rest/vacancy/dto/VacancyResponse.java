package org.jobboard.adapter.in.rest.vacancy.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.OffsetDateTime;
import java.util.UUID;

public record VacancyResponse(
	@Schema(example = "6001fbff-b90a-4784-84aa-4d17646119c8")
	UUID id,
	@Schema(example = "Java programming")
	String title,
	@Schema(example = "Required senior java developer", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	String comment,
	@Schema(example = "1000", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	Integer price,
	@Schema(example = "2024-01-01T10:00:00+03:00")
	OffsetDateTime createAt,
	@Schema(example = "2025-01-01T10:00:00+03:00")
	OffsetDateTime updateAt) {
}
