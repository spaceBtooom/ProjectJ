package com.spring.web.api.backend.hex.domain.vacancy.api.spring.webmvc.dto;

import java.time.ZonedDateTime;

public record VacancyResponse(String title,
					String comment,
					Integer price,
					ZonedDateTime createAt,
					ZonedDateTime updateAt) {
}
