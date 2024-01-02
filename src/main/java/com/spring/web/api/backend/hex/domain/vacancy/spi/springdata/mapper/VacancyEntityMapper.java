package com.spring.web.api.backend.hex.domain.vacancy.spi.springdata.mapper;

import com.spring.web.api.backend.hex.domain.vacancy.Vacancy;
import com.spring.web.api.backend.hex.domain.vacancy.spi.springdata.dbo.VacancyEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VacancyEntityMapper {
	Vacancy toDomain(VacancyEntity vacancyEntity);
	VacancyEntity toDbo(Vacancy vacancy);
}
