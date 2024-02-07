package org.jobboard.adapter.out.persistence.jpa.vacancy;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface JpaVacancyRepository extends CrudRepository<VacancyEntity, UUID> {
}
