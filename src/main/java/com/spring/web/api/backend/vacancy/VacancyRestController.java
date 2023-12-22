package com.spring.web.api.backend.vacancy;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vacancy")
@Tag(name="Vacancies API")
public class VacancyRestController {
	private final VacancyRepository vacancyRepository;

	public VacancyRestController(VacancyRepository vacancyRepository) {
		this.vacancyRepository = vacancyRepository;
	}
	@Operation(summary = "Get all vacancies", description = "Returns all vacancies")
	@ApiResponses(value={
		@ApiResponse(responseCode = "200", description = "Successfully retrieved")
	})
	@GetMapping("")
	List<Vacancy> getAllVacancies(){
		ArrayList<Vacancy> vacancies = new ArrayList<>();
		vacancyRepository
			.findAll()
			.forEach(vacancies::add);
		return vacancies;
	}

	@Operation(summary = "Get a vacancy by id", description = "Returns a vacancy as per the id")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "202", description = "Successfully retrieved"),
		@ApiResponse(responseCode = "404", description = "Not found - The vacancy was not found")
	})
	@GetMapping("/{id}")
	ResponseEntity<Vacancy> getVacancy(@PathVariable("id")
						     @Parameter(name ="id", description = "Vacancy id", example = "1")
							Long id) {
		Optional<Vacancy> vacancy = vacancyRepository.findById(id);
		return vacancy
		  .map(ResponseEntity::ok)
		  .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	@ApiResponses(value = {
		@ApiResponse(responseCode = "202", description = "Successfully retrieved"),
		@ApiResponse(responseCode = "400", description = "Bad request - No such vacancy",content = {})
	})
	@Operation(summary = "Save a vacancy", description = "Return created vacancy")
	@PostMapping("/")
	ResponseEntity<Vacancy> addVacancy(@RequestBody
						     @Parameter(name="vacancy", description = "Received vacancy")
						     Vacancy vacancy){
		return Optional.of(vacancyRepository.save(vacancy))
			.map(ResponseEntity::ok)
			.orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}
}
