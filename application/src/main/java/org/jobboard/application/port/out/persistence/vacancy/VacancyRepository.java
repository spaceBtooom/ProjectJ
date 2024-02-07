package org.jobboard.application.port.out.persistence.vacancy;


import org.jobboard.domain.vacancy.Vacancy;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VacancyRepository {
	List<Vacancy> findAll();
	Optional<Vacancy> findById(UUID id);
	Optional<Vacancy> save(Vacancy vacancy);
	void deleteById(UUID id);

	Optional<Vacancy> update(UUID id, Vacancy vacancy);
}

