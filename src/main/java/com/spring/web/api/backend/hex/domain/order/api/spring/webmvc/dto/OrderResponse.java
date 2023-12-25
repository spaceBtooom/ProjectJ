package com.spring.web.api.backend.hex.domain.order.api.spring.webmvc.dto;

import com.spring.web.api.backend.hex.domain.tag.api.spring.webmvc.dto.TagRequest;
import com.spring.web.api.backend.hex.domain.tag.api.spring.webmvc.dto.TagResponse;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public record OrderResponse (UUID id,
				     String title,
				     String comment,
				     Integer price,
				     String urlSource,
				     ZonedDateTime expireAt,
				     List<TagResponse> tags,
				     Map<String, String> files,
				     ZonedDateTime createdAt,
				     ZonedDateTime updatedAt) {
}

