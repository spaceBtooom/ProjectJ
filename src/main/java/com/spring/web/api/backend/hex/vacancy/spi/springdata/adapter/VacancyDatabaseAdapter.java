package com.spring.web.api.backend.hex.vacancy.spi.springdata.adapter;

import com.spring.web.api.backend.hex.vacancy.domain.Vacancy;
import com.spring.web.api.backend.hex.vacancy.spi.VacancySpi;
import com.spring.web.api.backend.hex.vacancy.spi.springdata.db.SpringDataVacancyRepository;
import com.spring.web.api.backend.hex.vacancy.spi.springdata.dbo.VacancyEntity;
import com.spring.web.api.backend.hex.vacancy.spi.springdata.mapper.generic.VacancyEntityGenericMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VacancyDatabaseAdapter implements VacancySpi {

	private final SpringDataVacancyRepository vacancyRepository;
	private final VacancyEntityGenericMapper vacancyMapper;

	public VacancyDatabaseAdapter(SpringDataVacancyRepository vacancyRepository, VacancyEntityGenericMapper vacancyMapper) {
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

	@Override
	public void deleteById(UUID id) {
		vacancyRepository.deleteById(id);
	}

	@Override
	@Transactional
	public Optional<Vacancy> update(UUID id, Vacancy vacancy) {
		Optional<VacancyEntity> oldEntityVacancy = vacancyRepository.findById(id);
		Vacancy oldVacancy = oldEntityVacancy
			.map(vacancyMapper::toDomain)
			.orElseThrow(()->new RuntimeException("Cannot find vacancy"));
		oldVacancy.updateVacancy(vacancy);
		return save(oldVacancy);
	}


}
