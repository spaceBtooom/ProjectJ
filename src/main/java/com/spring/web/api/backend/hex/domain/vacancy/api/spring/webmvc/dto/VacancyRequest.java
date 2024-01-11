package com.spring.web.api.backend.hex.domain.vacancy.api.spring.webmvc.dto;

import java.time.OffsetDateTime;

public record VacancyRequest (String title,
	String comment,
	Integer price){
}
