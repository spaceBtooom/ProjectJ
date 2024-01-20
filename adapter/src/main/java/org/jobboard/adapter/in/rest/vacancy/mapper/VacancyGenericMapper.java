package org.jobboard.adapter.in.rest.vacancy.mapper;

import org.jobboard.adapter.in.rest.common.mappers.GenericMapperRDR;
import org.jobboard.adapter.in.rest.vacancy.dto.VacancyRequest;
import org.jobboard.adapter.in.rest.vacancy.dto.VacancyResponse;
import org.jobboard.domain.vacancy.Vacancy;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VacancyGenericMapper implements GenericMapperRDR<VacancyRequest, Vacancy, VacancyResponse> {
	@Override
	public Vacancy toDomain(VacancyRequest vacancyRequest) {
		Vacancy vacancy = new Vacancy(UUID.randomUUID());
		vacancy.setTitle(vacancyRequest.title());
		vacancy.setComment(vacancyRequest.comment());
		vacancy.setPrice(vacancyRequest.price());
		return vacancy;
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
