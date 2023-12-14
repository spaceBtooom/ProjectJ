package com.spring.web.api.backend;

import com.spring.web.api.backend.domain.UserEntity;
import com.spring.web.api.backend.repositories.UserEntityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestInPeaceMyBruddaApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(UserEntityRepository.class);

	private final UserEntityRepository userEntityRepository;

	public RestInPeaceMyBruddaApplication(UserEntityRepository userEntityRepository) {
		this.userEntityRepository = userEntityRepository;
	}

	public static void main(String[] args) {
        SpringApplication.run(RestInPeaceMyBruddaApplication.class, args);
    }


	@Override
	public void run(String... args) throws Exception {
		log.info("Preloading " + userEntityRepository
			.save(UserEntity
			.builder()
				.username("bil")
				.name("billi")
			.build()));

		log.info("Preloading " + userEntityRepository
			.save(UserEntity
				.builder()
				.username("bob")
				.name("bobby")
				.build()));
	}
}
