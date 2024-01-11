package com.spring.web.api.backend.hex.domain.vacancy;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.UUID;

public class VacancyProvider {
	public static Vacancy getDefaultVacancyWithId(int id){
		return new Vacancy(UUID.randomUUID(),
			"id: " + id +" default vacancy",
			"comment",
			10000,
			OffsetDateTime.of(
				2021,
				9,
				13,
				13,
				40,
				9,
				0,
				ZoneOffset.of("+03:00")),
			OffsetDateTime.now());
	}
}
