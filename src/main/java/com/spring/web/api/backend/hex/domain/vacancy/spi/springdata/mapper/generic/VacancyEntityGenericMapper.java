package com.spring.web.api.backend.hex.domain.vacancy.spi.springdata.mapper.generic;

import com.spring.web.api.backend.GenericMapperDE;
import com.spring.web.api.backend.hex.domain.vacancy.Vacancy;
import com.spring.web.api.backend.hex.domain.vacancy.spi.springdata.dbo.VacancyEntity;
import org.springframework.stereotype.Service;

import java.time.ZoneId;

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
	public Vacancy toDomain(VacancyEntity vacancyEntity) {
		return new Vacancy(vacancyEntity.getId(),
			vacancyEntity.getTitle(),
			vacancyEntity.getComment(),
			vacancyEntity.getPrice(),
			vacancyEntity.getCreateAt(),
			vacancyEntity.getUpdateAt());
	}
}
