package com.spring.web.api.backend.hex.domain.vacancy.api.spring.webmvc.mapper;

import com.spring.web.api.backend.hex.domain.vacancy.Vacancy;
import com.spring.web.api.backend.hex.domain.vacancy.api.spring.webmvc.dto.VacancyRequest;
import com.spring.web.api.backend.hex.domain.vacancy.api.spring.webmvc.dto.VacancyResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VacancyMapper {
	VacancyResponse toResponse(Vacancy vacancy);
	Vacancy toVacancy(VacancyRequest vacancyRequest);
}
