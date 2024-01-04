package com.spring.web.api.backend;

import com.spring.web.api.backend.hex.domain.order.spi.springdata.mapper.GenericMapper.OrderEntityGenericMapper;
import com.spring.web.api.backend.hex.domain.orderFile.api.useCase.OrderFileSystemUseCase;
import com.spring.web.api.backend.hex.domain.orderFile.spi.springdata.adapter.OrderFileDatabaseAdapter;
import com.spring.web.api.backend.hex.domain.orderFile.spi.springdata.db.SpringDataOrderFileRepository;
import com.spring.web.api.backend.hex.domain.orderFile.spi.springdata.mapper.GenericMapper.OrderFileEntityGenericMapper;
import com.spring.web.api.backend.hex.domain.tag.spi.springdata.mapper.GenericMapper.TagEntityGenericMapper;
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
	public OrderUseCase orderUseCase(SpringDataOrderRepository orderRepository, SpringDataTagRepository tagRepository, OrderEntityGenericMapper orderMapper) {
		return new OrderUseCase(new OrderDatabaseAdapter(orderRepository, tagRepository, orderMapper));
	}

	@Bean
	public TagUseCase tagUseCase(SpringDataTagRepository tagRepository, TagEntityGenericMapper tagMapper) {
		return new TagUseCase(new TagDatabaseAdapter(tagRepository, tagMapper));
	}

	@Bean
	public VacancyUseCase vacancyUseCase(SpringDataVacancyRepository vacancyRepository, VacancyEntityMapper vacancyMapper){
		return new VacancyUseCase(new VacancyDatabaseAdapter(vacancyRepository, vacancyMapper));
	}

	@Bean
	public OrderFileSystemUseCase orderFileSystemUseCase(SpringDataOrderRepository orderRepository, SpringDataTagRepository tagRepository, OrderEntityGenericMapper orderMapper,  SpringDataOrderFileRepository orderFileRepository, OrderFileEntityGenericMapper mapper){
		return new OrderFileSystemUseCase(new OrderDatabaseAdapter(orderRepository, tagRepository, orderMapper), new OrderFileDatabaseAdapter(orderFileRepository,mapper));
	}


}
