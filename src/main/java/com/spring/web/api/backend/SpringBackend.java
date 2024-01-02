package com.spring.web.api.backend;

import com.spring.web.api.backend.hex.domain.order.spi.springdata.mapper.OrderEntityMapper;
import com.spring.web.api.backend.hex.domain.tag.spi.springdata.mapper.TagEntityMapper;
import com.spring.web.api.backend.hex.domain.order.spi.springdata.adapter.OrderDatabaseAdapter;
import com.spring.web.api.backend.hex.domain.order.spi.springdata.db.SpringDataOrderRepository;
import com.spring.web.api.backend.hex.domain.tag.spi.springdata.db.SpringDataTagRepository;
import com.spring.web.api.backend.hex.domain.tag.spi.springdata.spi.TagDatabaseAdapter;
import com.spring.web.api.backend.hex.domain.order.api.useCase.OrderUseCase;
import com.spring.web.api.backend.hex.domain.tag.api.useCase.TagUseCase;
import com.spring.web.api.backend.hex.domain.vacancy.api.useCase.VacancyUseCase;
import com.spring.web.api.backend.hex.domain.vacancy.spi.springdata.adapter.VacancyDatabaseAdapter;
import com.spring.web.api.backend.hex.domain.vacancy.spi.springdata.db.SpringDataVacancyRepository;
import com.spring.web.api.backend.hex.domain.vacancy.spi.springdata.mapper.VacancyEntityMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBackend {
	public static void main(String[] args) {
		SpringApplication.run(SpringBackend.class, args);
	}

	@Bean
	public OrderUseCase orderUseCase(SpringDataOrderRepository orderRepository, SpringDataTagRepository tagRepository, OrderEntityMapper orderMapper) {
		return new OrderUseCase(new OrderDatabaseAdapter(orderRepository, tagRepository, orderMapper));
	}

	@Bean
	public TagUseCase tagUseCase(SpringDataTagRepository tagRepository, TagEntityMapper tagMapper) {
		return new TagUseCase(new TagDatabaseAdapter(tagRepository, tagMapper));
	}

	@Bean
	public VacancyUseCase vacancyUseCase(SpringDataVacancyRepository vacancyRepository, VacancyEntityMapper vacancyMapper){
		return new VacancyUseCase(new VacancyDatabaseAdapter(vacancyRepository, vacancyMapper));
	}


}
