package com.spring.web.api.backend.hex.order.api.spring.webmvc.dto;

import com.spring.web.api.backend.hex.orderFile.api.spring.webmvc.dto.OrderFileResponse;
import com.spring.web.api.backend.hex.tag.api.spring.webmvc.dto.TagResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public record OrderResponse(
	@Schema(example = "2186d7f6-fc64-4f46-9770-6ab5301bbe7f", requiredMode = Schema.RequiredMode.REQUIRED)
	UUID id,
	@Schema(example = "Creating map for a truck traffic on a subway...", requiredMode = Schema.RequiredMode.REQUIRED)
	String title,

	@Schema(example = "Needed to use local files to parse...", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	String comment,

	@Schema(example = "1000",requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	Integer price,
	@Schema(example = "https://www.baeldung.com/spring-boot-clean-architecture", requiredMode = Schema.RequiredMode.REQUIRED)
	String urlSource,

	@Schema(example = "2025-01-01T10:00:00+03:00")
	OffsetDateTime expireAt,

	@Schema(exampleClasses = TagResponse.class)
	List<TagResponse> tags,

	@Schema(exampleClasses = {OrderFileResponse.class, OrderFileResponse.class})
	List<OrderFileResponse> files,
	@Schema(example = "2024-01-01T10:00:00+03:00")
	OffsetDateTime createdAt,
	@Schema(example = "2024-01-01T11:00:00+03:00")
	OffsetDateTime updatedAt) {
}

