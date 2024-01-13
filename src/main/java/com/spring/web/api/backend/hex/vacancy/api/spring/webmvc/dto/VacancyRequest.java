package com.spring.web.api.backend.hex.vacancy.api.spring.webmvc.dto;

import io.swagger.v3.oas.annotations.media.Schema;
public record VacancyRequest (
	@Schema(example = "Java programming")
	String title,
	@Schema(example = "Required senior java developer", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	String comment,

	@Schema(example = "1000", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	Integer price){
}
