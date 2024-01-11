package com.spring.web.api.backend.hex.domain.order.api.spring.webmvc.dto;

import com.spring.web.api.backend.hex.domain.tag.api.spring.webmvc.dto.TagRequest;

import java.time.OffsetDateTime;
import java.util.List;

public record OrderRequest(String title,
				   String comment,
				   Integer price,
				   String urlSource,
				   OffsetDateTime expireAt,
				   List<TagRequest> tags) {
}
