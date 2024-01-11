package com.spring.web.api.backend.hex.domain.vacancy.spi;

import com.spring.web.api.backend.hex.domain.vacancy.Vacancy;
import com.spring.web.api.backend.hex.domain.vacancy.VacancyProvider;
import com.spring.web.api.backend.hex.domain.vacancy.spi.springdata.dbo.VacancyEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class VacancySpiTest {

	@Autowired
	private VacancySpi underTest;
	@Test
	void itShouldFindAllVacancies() {
		Vacancy vacancy1 = VacancyProvider.getDefaultVacancyWithId(1);
		Vacancy vacancy2 = VacancyProvider.getDefaultVacancyWithId(2);


		underTest.save(vacancy1);
		underTest.save(vacancy2);

		List<Vacancy> vacancies = List.of(vacancy1, vacancy2);
		List<Vacancy> vacanciesDd= underTest.findAll();
		boolean res = vacancies.equals(vacanciesDd);
		assertThat(res).isTrue();
	}

	@Test
	void findById() {
	}

	@Test
	void save() {
	}
}
