package org.jobboard.bootstrap;


import jakarta.persistence.Entity;
import org.jobboard.application.port.in.order.OrderUseCase;
import org.jobboard.application.port.in.orderFile.AttachedOrderFileUseCase;
import org.jobboard.application.port.in.tag.TagUseCase;
import org.jobboard.application.port.in.vacancy.VacancyUseCase;
import org.jobboard.application.port.out.persistence.attachedOderFile.AttachedOrderFileRepository;
import org.jobboard.application.port.out.persistence.order.OrderRepository;
import org.jobboard.application.port.out.persistence.tag.TagRepository;
import org.jobboard.application.port.out.persistence.vacancy.VacancyRepository;
import org.jobboard.application.service.order.OrderService;
import org.jobboard.application.service.orderFile.AttachedOrderFileSystemService;
import org.jobboard.application.service.tag.TagService;
import org.jobboard.application.service.vacancy.VacancyService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "org.jobboard")
@EnableJpaRepositories(basePackages = "org.jobboard")
@EntityScan(basePackages = "org.jobboard")

public class SpringBackend {
	public static void main(String[] args) {
		SpringApplication.run(SpringBackend.class, args);
	}

	@Bean
	public OrderUseCase orderUseCase(OrderRepository orderRepository, TagUseCase tagUseCase) {
		return new OrderService(orderRepository, tagUseCase);
	}

	@Bean
	public TagUseCase tagUseCase(TagRepository tagRepository) {
		return new TagService(tagRepository);
	}

	@Bean
	public VacancyUseCase vacancyUseCase(VacancyRepository vacancyRepository){

		return new VacancyService(vacancyRepository);
	}

	@Bean
	public AttachedOrderFileUseCase attachedOrderFileUseCase(AttachedOrderFileRepository attachedOrderFileRepository,
								 OrderUseCase orderUseCase){
		return new AttachedOrderFileSystemService(attachedOrderFileRepository, orderUseCase);
	}


}
