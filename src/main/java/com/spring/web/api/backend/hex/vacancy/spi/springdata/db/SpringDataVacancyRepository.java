package com.spring.web.api.backend.hex.vacancy.spi.springdata.db;

import com.spring.web.api.backend.hex.vacancy.spi.springdata.dbo.VacancyEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SpringDataVacancyRepository extends CrudRepository<VacancyEntity, UUID> {
}
