package org.jobboard.adapter.in.rest.vacancy.dto;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public record VacancyRequest (
	@Schema(example = "Java programming")
	String title,
	@Schema(example = "Required senior java developer",
		requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	String comment,
	@Schema(example = "1000",
		requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	Integer price){
}
