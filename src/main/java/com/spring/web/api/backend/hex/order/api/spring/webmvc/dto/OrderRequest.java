package com.spring.web.api.backend.hex.order.api.spring.webmvc.dto;

import com.spring.web.api.backend.hex.tag.api.spring.webmvc.dto.TagRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public record OrderRequest(
	@Schema(example = "Create DB on MySQL")
	String title,
	@Schema(example = "Needed to entities. For example ...")
	String comment,
	@Schema(example = "1000", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	Integer price,
	@Schema(example = "https://blog.cleancoder.com/uncle-bob/2011/09/30/Screaming-Architecture.html")
	String urlSource,

	@Future
	@Schema(example = "2025-01-01T10:00:00+03:00")
	OffsetDateTime expireAt,

	@Schema(exampleClasses = TagRequest.class)
	List<TagRequest> tags) {
	@Override
	public List<TagRequest> tags() {
		return tags == null
			? new ArrayList<>()
			: tags;
	}
}
