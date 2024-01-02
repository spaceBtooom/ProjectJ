package com.spring.web.api.backend.hex.domain.vacancy.api.spring.webmvc;

import com.spring.web.api.backend.hex.domain.vacancy.Vacancy;
import com.spring.web.api.backend.hex.domain.vacancy.api.VacancyApi;
import com.spring.web.api.backend.hex.domain.vacancy.api.spring.webmvc.dto.VacancyRequest;
import com.spring.web.api.backend.hex.domain.vacancy.api.spring.webmvc.dto.VacancyResponse;
import com.spring.web.api.backend.hex.domain.vacancy.api.spring.webmvc.mapper.VacancyMapper;
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
import java.util.UUID;

@RestController
@RequestMapping("/vacancy")
@Tag(name="Vacancies API")
public class VacancyRestController {
	private final VacancyApi vacancyApi;
	private final VacancyMapper vacancyMapper;

	public VacancyRestController(VacancyApi vacancyApi, VacancyMapper vacancyMapper) {
		this.vacancyApi = vacancyApi;
		this.vacancyMapper = vacancyMapper;
	}

	@Operation(summary = "Get all vacancies", description = "Returns all vacancies")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Successfully retrieved")
	})
	@GetMapping("")
	List<VacancyResponse> getAllVacancies() {
		ArrayList<VacancyResponse> vacancies = new ArrayList<>();
		vacancyApi
			.findAll()
			.forEach(vacancy -> vacancies.add(vacancyMapper.toResponse(vacancy)));
		return vacancies;
	}

	@Operation(summary = "Get a vacancy by id", description = "Returns a vacancy as per the id")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "202", description = "Successfully retrieved"),
		@ApiResponse(responseCode = "404", description = "Not found - The vacancy was not found")
	})
	@GetMapping("/{id}")
	ResponseEntity<VacancyResponse> getVacancy(@PathVariable("id")
								 @Parameter(name = "id", description = "Vacancy id", example = "1")
								 UUID id) {
		return vacancyApi.findById(id)
			.map(vacancy ->
				ResponseEntity
					.ok(vacancyMapper.toResponse(vacancy)))
			.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@ApiResponses(value = {
		@ApiResponse(responseCode = "202", description = "Successfully retrieved"),
		@ApiResponse(responseCode = "400", description = "Bad request - No such vacancy", content = {})
	})
	@Operation(summary = "Save a vacancy", description = "Return created vacancy")
	@PostMapping("/")
	ResponseEntity<VacancyResponse> addVacancy(@RequestBody
								 @Parameter(name = "vacancy", description = "Received vacancy")
								 VacancyRequest vacancyRequest) {
		return vacancyApi.save(vacancyMapper.toVacancy(vacancyRequest))
			.map(vacancy-> ResponseEntity
				.ok(vacancyMapper.toResponse(vacancy)))
			.orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}
}
