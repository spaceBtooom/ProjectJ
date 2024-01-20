package org.jobboard.application.service.vacancy;

import org.jobboard.application.port.in.vacancy.VacancyUseCase;
import org.jobboard.application.port.out.persistence.vacancy.VacancyRepository;
import org.jobboard.domain.vacancy.Vacancy;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class VacancyService implements VacancyUseCase {
	private final VacancyRepository vacancyRepository;

	public VacancyService(VacancyRepository vacancyRepository) {
		this.vacancyRepository = vacancyRepository;
	}

	@Override
	public List<Vacancy> findAll() {
		return vacancyRepository.findAll();
	}

	@Override
	public Optional<Vacancy> findById(UUID id) {
		return vacancyRepository.findById(id);
	}

	@Override
	public Optional<Vacancy> save(Vacancy vacancy) {
		return vacancyRepository.save(vacancy);
	}

	@Override
	public void deleteById(UUID id) {
		vacancyRepository.deleteById(id);
	}

	@Override
	public Optional<Vacancy> update(UUID id, Vacancy vacancy) {
		return vacancyRepository.update(id, vacancy);
	}
}
