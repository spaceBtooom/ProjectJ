package com.spring.web.api.backend.hex.domain.order.api.spring.webmvc.dto;

import com.spring.web.api.backend.hex.domain.orderFile.OrderFile;
import com.spring.web.api.backend.hex.domain.orderFile.api.spring.webmvc.dto.OrderFileResponse;
import com.spring.web.api.backend.hex.domain.tag.api.spring.webmvc.dto.TagRequest;
import com.spring.web.api.backend.hex.domain.tag.api.spring.webmvc.dto.TagResponse;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public record OrderResponse (UUID id,
				     String title,
				     String comment,
				     Integer price,
				     String urlSource,
				     OffsetDateTime expireAt,
				     List<TagResponse> tags,
				     List<OrderFileResponse> files,
				     OffsetDateTime createdAt,
				     OffsetDateTime updatedAt) {
}

