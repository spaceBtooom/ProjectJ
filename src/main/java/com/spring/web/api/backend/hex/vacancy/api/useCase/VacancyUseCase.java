package com.spring.web.api.backend.hex.vacancy.api.useCase;

import com.spring.web.api.backend.hex.vacancy.domain.Vacancy;
import com.spring.web.api.backend.hex.vacancy.api.VacancyApi;
import com.spring.web.api.backend.hex.vacancy.spi.VacancySpi;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class VacancyUseCase implements VacancyApi {
	private final VacancySpi vacancySpi;

	public VacancyUseCase(VacancySpi vacancySpi) {
		this.vacancySpi = vacancySpi;
	}

	@Override
	public List<Vacancy> findAll() {
		return vacancySpi.findAll();
	}

	@Override
	public Optional<Vacancy> findById(UUID id) {
		return vacancySpi.findById(id);
	}

	@Override
	public Optional<Vacancy> save(Vacancy vacancy) {
		return vacancySpi.save(vacancy);
	}

	@Override
	public void deleteById(UUID id) {
		vacancySpi.deleteById(id);
	}

	@Override
	public Optional<Vacancy> update(UUID id, Vacancy vacancy) {
		return vacancySpi.update(id, vacancy);
	}
}
