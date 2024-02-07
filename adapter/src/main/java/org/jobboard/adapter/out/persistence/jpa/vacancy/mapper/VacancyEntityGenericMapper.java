package org.jobboard.adapter.out.persistence.jpa.vacancy.mapper;

import org.jobboard.adapter.out.persistence.jpa.common.mapper.GenericMapperDE;
import org.jobboard.adapter.out.persistence.jpa.vacancy.VacancyEntity;
import org.jobboard.domain.vacancy.Vacancy;
import org.springframework.stereotype.Service;

@Service
public class VacancyEntityGenericMapper implements GenericMapperDE<Vacancy, VacancyEntity> {
	@Override
	public VacancyEntity toDbo(Vacancy vacancy) {
		return new VacancyEntity(vacancy.getId(),
			vacancy.getTitle(),
			vacancy.getComment(),
			vacancy.getPrice(),
			vacancy.getCreateAt(),
			vacancy.getUpdateAt());
	}

	@Override
	public Vacancy toDomain(VacancyEntity ve) {
		Vacancy vacancy = new Vacancy(ve.getId());
		vacancy.setTitle(ve.getTitle());
		vacancy.setComment(ve.getComment());
		vacancy.setPrice(ve.getPrice());
		vacancy.setCreateAt(ve.getCreateAt());
		vacancy.setUpdateAt(ve.getUpdateAt());
		return vacancy;
	}
}
