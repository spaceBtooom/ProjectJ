package com.spring.web.api.backend.hex.domain.vacancy.spi;

import com.spring.web.api.backend.hex.domain.vacancy.Vacancy;
import com.spring.web.api.backend.hex.domain.vacancy.spi.springdata.dbo.VacancyEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class VacancySpiTest {

	@Autowired
	private VacancySpi underTest;
	@Test
	void itShouldFindAllVacancies() {
		Vacancy vacancy1 = new Vacancy(UUID.randomUUID(),
			"Java Developer1",
			"Required1",
			10000);
		Vacancy vacancy2 = new Vacancy(UUID.randomUUID(),
			"Java Developer2",
			"Required2",
			10000);
		underTest.save(vacancy1);
		underTest.save(vacancy2);

		List<Vacancy> vacancies = List.of(vacancy1, vacancy2);
		assertThat(vacancies).isEqualTo(underTest.findAll());




	}

	@Test
	void findById() {
	}

	@Test
	void save() {
	}
}
