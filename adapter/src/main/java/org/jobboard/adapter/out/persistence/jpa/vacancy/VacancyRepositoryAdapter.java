package org.jobboard.adapter.out.persistence.jpa.vacancy;

import jakarta.transaction.Transactional;
import org.jobboard.adapter.out.persistence.jpa.vacancy.mapper.VacancyEntityGenericMapper;
import org.jobboard.application.port.out.persistence.vacancy.VacancyRepository;
import org.jobboard.domain.vacancy.Vacancy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VacancyRepositoryAdapter implements VacancyRepository {

	private final JpaVacancyRepository repository;
	private final VacancyEntityGenericMapper vacancyMapper;

	public VacancyRepositoryAdapter(JpaVacancyRepository repository, VacancyEntityGenericMapper vacancyMapper) {
		this.repository = repository;
		this.vacancyMapper = vacancyMapper;
	}


	@Override
	public List<Vacancy> findAll() {
		List<Vacancy> vacancies = new ArrayList<>();
		repository
			.findAll()
			.forEach(vacancyEntity ->
				vacancies.add(vacancyMapper
					.toDomain(vacancyEntity)));
		return vacancies;
	}

	@Override
	public Optional<Vacancy> findById(UUID id) {
		return Optional.of(vacancyMapper
			.toDomain(repository
				.findById(id)
				.orElseThrow(() -> new RuntimeException("Unable to find vacancy id db"))));
	}

	@Override
	public Optional<Vacancy> save(Vacancy vacancy) {
		VacancyEntity entity = repository.save(vacancyMapper
			.toDbo(vacancy));
		return Optional.of(vacancyMapper
			.toDomain(entity));
	}

	@Override
	public void deleteById(UUID id) {
		repository.deleteById(id);
	}

	@Override
	@Transactional
	public Optional<Vacancy> update(UUID id, Vacancy vacancy) {
		Optional<VacancyEntity> oldEntityVacancy = repository.findById(id);
		Vacancy oldVacancy = oldEntityVacancy
			.map(vacancyMapper::toDomain)
			.orElseThrow(() -> new RuntimeException("Cannot find vacancy"));
		oldVacancy.updateVacancy(vacancy);
		Optional<Vacancy> save = save(oldVacancy);
		return save;
	}


}
