package com.spring.web.api.backend.hex.domain.vacancy.api.spring.webmvc.mapper.generic;

import com.spring.web.api.backend.GenericMapperRDR;
import com.spring.web.api.backend.hex.domain.vacancy.Vacancy;
import com.spring.web.api.backend.hex.domain.vacancy.api.spring.webmvc.dto.VacancyRequest;
import com.spring.web.api.backend.hex.domain.vacancy.api.spring.webmvc.dto.VacancyResponse;
import org.springframework.stereotype.Service;

@Service
public class VacancyGenericMapper implements GenericMapperRDR<VacancyRequest, Vacancy, VacancyResponse> {
	@Override
	public Vacancy toDomain(VacancyRequest vacancyRequest) {
		return new Vacancy(vacancyRequest.title(),
			vacancyRequest.comment(),
			vacancyRequest.price());
	}

	@Override
	public VacancyResponse toResponse(Vacancy vacancy) {
		return new VacancyResponse(vacancy.getId(),
			vacancy.getTitle(),
			vacancy.getComment(),
			vacancy.getPrice(),
			vacancy.getCreateAt(),
			vacancy.getUpdateAt());
	}
}
