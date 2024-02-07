package org.jobboard.adapter.in.rest.order.dto;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public record OrderRequest(
	@Schema(example = "Create DB on MySQL")
	String title,
	@Schema(example = "Needed to entities. For example ...")
	String comment,
	@Schema(example = "1000",
		requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	Integer price,
	@Schema(example = "https://blog.cleancoder.com/uncle-bob/2011/09/30/Screaming-Architecture.html",
		requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	String urlSource,

	@Future
	@Schema(example = "2025-01-01T10:00:00+03:00")
	OffsetDateTime expireAt,

	@ArraySchema(arraySchema = @Schema(
		description = "My description",
		example ="[\"Java\", \"Python\"]"))
	Optional<List<String>> tagNames) {
}
