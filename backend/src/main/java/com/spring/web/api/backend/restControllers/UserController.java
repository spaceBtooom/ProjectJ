package com.spring.web.api.backend.restControllers;

import com.spring.web.api.backend.domain.UserEntity;
import com.spring.web.api.backend.repositories.UserEntityRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController("/user")
public class UserController {

	private final UserEntityRepository repository;

	UserController(UserEntityRepository repository){
		this.repository = repository;
	}

	@GetMapping("/all")
	List<UserEntity> getAllUsers(){
		List<UserEntity> result = new ArrayList<>();
		repository.findAll().forEach(result::add);

		return result;
	}


}
