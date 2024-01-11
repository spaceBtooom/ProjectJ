package com.spring.web.api.backend.hex.domain.vacancy.api.spring.webmvc.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public record VacancyResponse(UUID id,
					String title,
					String comment,
					Integer price,
					OffsetDateTime createAt,
					OffsetDateTime updateAt) {
}
