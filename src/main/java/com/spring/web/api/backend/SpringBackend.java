package com.spring.web.api.backend;

import com.spring.web.api.backend.hex.domain.db.springdata.mapper.OrderEntityMapper;
import com.spring.web.api.backend.hex.domain.db.springdata.mapper.TagEntityMapper;
import com.spring.web.api.backend.hex.domain.db.springdata.spi.OrderDboRepository;
import com.spring.web.api.backend.hex.domain.db.springdata.spi.SpringDataOrderRepository;
import com.spring.web.api.backend.hex.domain.db.springdata.spi.SpringDataTagRepository;
import com.spring.web.api.backend.hex.domain.db.springdata.spi.TagDboRepository;
import com.spring.web.api.backend.hex.domain.order.api.useCase.OrderUseCase;
import com.spring.web.api.backend.hex.domain.tag.api.useCase.TagUseCase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBackend {
	public static void main(String[] args) {
		SpringApplication.run(SpringBackend.class, args);
	}

	@Bean
	public OrderUseCase orderUseCase(SpringDataOrderRepository orderRepository, OrderEntityMapper orderMapper) {
		return new OrderUseCase(new OrderDboRepository(orderRepository, orderMapper));
	}

	@Bean
	public TagUseCase tagUseCase(SpringDataTagRepository tagRepository, TagEntityMapper tagMapper) {
		return new TagUseCase(new TagDboRepository(tagRepository, tagMapper));
	}


}
