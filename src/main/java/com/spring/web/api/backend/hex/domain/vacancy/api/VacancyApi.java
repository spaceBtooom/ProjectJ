package com.spring.web.api.backend.hex.domain.vacancy.api;

import com.spring.web.api.backend.hex.domain.vacancy.Vacancy;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface VacancyApi {
	List<Vacancy> findAll();
	Optional<Vacancy> findById(UUID id);
	Optional<Vacancy> save(Vacancy vacancy);
}
