package com.spring.web.api.backend.hex.vacancy.api.spring.webmvc;

import com.spring.web.api.backend.hex.vacancy.api.VacancyApi;
import com.spring.web.api.backend.hex.vacancy.api.spring.webmvc.dto.VacancyRequest;
import com.spring.web.api.backend.hex.vacancy.api.spring.webmvc.dto.VacancyResponse;
import com.spring.web.api.backend.hex.vacancy.api.spring.webmvc.mapper.generic.VacancyGenericMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Tag(name = "Vacancies API")
public class VacancyRestController {
	private final VacancyApi vacancyApi;
	private final VacancyGenericMapper vacancyMapper;

	public VacancyRestController(VacancyApi vacancyApi, VacancyGenericMapper vacancyMapper) {
		this.vacancyApi = vacancyApi;
		this.vacancyMapper = vacancyMapper;
	}

	@Operation(summary = "Get all vacancies", description = "Returns all vacancies")
	@ApiResponses(value = {@ApiResponse(responseCode = "202", description = "Successfully retrieved", content = @Content(mediaType = "application/json", schema = @Schema(implementation = VacancyResponse.class)))})
	@GetMapping("")
	ResponseEntity<?> getAllVacancies() {
		ArrayList<VacancyResponse> vacancies = new ArrayList<>();
		vacancyApi.findAll().
			forEach(vacancy -> vacancies.add(vacancyMapper.toResponse(vacancy)));
		return ResponseEntity.ok(vacancies);
	}

	@Operation(summary = "Get a vacancy by id", description = "Returns a vacancy as per the id")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "202", description = "Successfully retrieved"),
		@ApiResponse(responseCode = "404", description = "Not found - The vacancy was not found", content = @Content(
			mediaType = "application/json",
			schema = @Schema(implementation = Void.class)))
	})

	@GetMapping("/{id}")
	ResponseEntity<VacancyResponse> getVacancy(@PathVariable("id")
						   @Parameter(name = "id", description = "Vacancy id", example = "6001fbff-b90a-4784-84aa-4d17646119c8")
						   UUID id) {
		return vacancyApi.findById(id)
			.map(vacancy -> ResponseEntity.ok(vacancyMapper.toResponse(vacancy)))
			.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@ApiResponses(value = {
		@ApiResponse(responseCode = "202", description = "Successfully retrieved"),
		@ApiResponse(responseCode = "400", description = "Bad request - No such vacancy", content = @Content(
			mediaType = "application/json",
			schema = @Schema(implementation = Void.class)))})
	@Operation(summary = "Save a vacancy", description = "Return created vacancy")
	@PostMapping("/")
	ResponseEntity<VacancyResponse> addVacancy(@RequestBody VacancyRequest vacancyRequest) {
		return vacancyApi
			.save(vacancyMapper.toDomain(vacancyRequest))
			.map(vacancy -> ResponseEntity.ok(vacancyMapper.toResponse(vacancy)))
			.orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}

	@ApiResponses(value = {
		@ApiResponse(responseCode = "200",description = "Succesfully deleted")
	})
	@Operation(summary = "Delete vacancy")
	@DeleteMapping("/{id}")
	ResponseEntity<?> deleteVacancy(@PathVariable(name="id")
			   @Parameter(name = "id", description = "id deleting vacancy", example = "6001fbff-b90a-4784-84aa-4d17646119c8")
			   UUID id) {
		vacancyApi.deleteById(id);
		return ResponseEntity.ok()
			.build();
	}
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200",description = "Succesfully deleted")
	})
	@Operation(summary = "Update vacancy")
	@PatchMapping("/{id}")
	ResponseEntity<?> updateVacancy(@PathVariable(name="id")
					@Parameter(name = "id", description = "id updating vacancy", example = "6001fbff-b90a-4784-84aa-4d17646119c8")
					UUID id,
					@RequestBody
					VacancyRequest vacancyRequest) {
		return vacancyApi.update(id, vacancyMapper.toDomain(vacancyRequest))
			.map(vacancy -> ResponseEntity.ok(vacancyMapper.toResponse(vacancy)))
			.orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}

}
