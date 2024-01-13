package com.spring.web.api.backend.hex.vacancy;

import com.spring.web.api.backend.hex.vacancy.domain.Vacancy;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
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
