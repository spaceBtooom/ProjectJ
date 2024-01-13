package com.spring.web.api.backend;

import com.spring.web.api.backend.hex.order.spi.OrderSpi;
import com.spring.web.api.backend.hex.orderFile.api.useCase.OrderFileSystemUseCase;
import com.spring.web.api.backend.hex.orderFile.spi.OrderFileSpi;
import com.spring.web.api.backend.hex.tag.api.TagApi;
import com.spring.web.api.backend.hex.tag.spi.TagSpi;
import com.spring.web.api.backend.hex.order.api.useCase.OrderUseCase;
import com.spring.web.api.backend.hex.tag.api.useCase.TagUseCase;
import com.spring.web.api.backend.hex.vacancy.api.useCase.VacancyUseCase;
import com.spring.web.api.backend.hex.vacancy.spi.VacancySpi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBackend {
	public static void main(String[] args) {
		SpringApplication.run(SpringBackend.class, args);
	}

	@Bean
	public OrderUseCase orderUseCase(OrderSpi orderSpi, TagApi tagApi) {
		return new OrderUseCase(orderSpi, tagApi);
	}

	@Bean
	public TagUseCase tagUseCase(TagSpi tagSpi) {
		return new TagUseCase(tagSpi);
	}

	@Bean
	public VacancyUseCase vacancyUseCase(VacancySpi vacancySpi){
		return new VacancyUseCase(vacancySpi);
	}

	@Bean
	public OrderFileSystemUseCase orderFileSystemUseCase(OrderSpi orderSpi, OrderFileSpi orderFileSpi){
		return new OrderFileSystemUseCase(orderSpi, orderFileSpi);
	}


}
