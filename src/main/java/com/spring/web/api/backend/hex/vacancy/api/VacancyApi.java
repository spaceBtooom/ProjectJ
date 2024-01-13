package com.spring.web.api.backend.hex.vacancy.api;

import com.spring.web.api.backend.hex.vacancy.domain.Vacancy;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VacancyApi {
	List<Vacancy> findAll();
	Optional<Vacancy> findById(UUID id);
	Optional<Vacancy> save(Vacancy vacancy);
	void deleteById(UUID id);

	Optional<Vacancy> update(UUID id,Vacancy domain);
}
