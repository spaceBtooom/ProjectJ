package com.spring.web.api.backend.hex.domain.vacancy.spi.springdata.adapter;

import com.spring.web.api.backend.hex.domain.vacancy.Vacancy;
import com.spring.web.api.backend.hex.domain.vacancy.spi.VacancySpi;
import com.spring.web.api.backend.hex.domain.vacancy.spi.springdata.db.SpringDataVacancyRepository;
import com.spring.web.api.backend.hex.domain.vacancy.spi.springdata.mapper.VacancyEntityMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class VacancyDatabaseAdapter implements VacancySpi {

	private final SpringDataVacancyRepository vacancyRepository;
	private final VacancyEntityMapper vacancyMapper;

	public VacancyDatabaseAdapter(SpringDataVacancyRepository vacancyRepository, VacancyEntityMapper vacancyMapper) {
		this.vacancyRepository = vacancyRepository;
		this.vacancyMapper = vacancyMapper;
	}

	@Override
	public List<Vacancy> findAll() {
		List<Vacancy> vacancies = new ArrayList<>();
		vacancyRepository.findAll().forEach(vacancyEntity -> vacancies.add(vacancyMapper.toDomain(vacancyEntity)));
		return vacancies;
	}

	@Override
	public Optional<Vacancy> findById(UUID id) {
		return Optional.of(vacancyMapper
				.toDomain(vacancyRepository
					.findById(id)
					.orElseThrow(() -> new RuntimeException("Unable to find vacancy id db"))));
	}

	@Override
	public Optional<Vacancy> save(Vacancy vacancy) {
		return Optional.of(vacancyMapper
			.toDomain(vacancyRepository
				.save(vacancyMapper
					.toDbo(vacancy))));
	}
}
