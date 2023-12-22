package com.spring.web.api.backend.tag;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/order-tags")
@Tag(name = "Admin setting: Order tags API")
@Log4j2
public class OrderTagRestController {

	private final OrderTagRepository orderTagRepository;
	private final AliasRepository aliasRepository;

	public OrderTagRestController(OrderTagRepository orderTagRepository,
						AliasRepository aliasRepository) {
		this.orderTagRepository = orderTagRepository;
		this.aliasRepository = aliasRepository;
	}

	@Operation(summary = "Get all tags", description = "Returns all tags")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Successfully retrieved")
	})
	@GetMapping("/")
	List<OrderTag> getAllTags() {
		ArrayList<OrderTag> tags = new ArrayList<>();
		orderTagRepository
			.findAll()
			.forEach(tags::add);
		return tags;
	}

	@Operation(summary = "Get a tag by id", description = "Returns a tag as per the id")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "202", description = "Successfully retrieved"),
		@ApiResponse(responseCode = "404", description = "Not found - The tag was not found")
	})
	@GetMapping("/{id}")
	ResponseEntity<OrderTag> getOrderTag(@PathVariable("id")
							 @Parameter(name = "id", description = "Tag id", example = "1")
							 Long id) {
		Optional<OrderTag> orderTag = orderTagRepository.findById(id);
		return orderTag
			.map(ResponseEntity::ok)
			.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@ApiResponses(value = {
		@ApiResponse(responseCode = "202", description = "Successfully retrieved"),
		@ApiResponse(responseCode = "400", description = "Bad request - No such tag", content = {})
	})
	@Operation(summary = "Save a tag", description = "Return created tag")
	@PostMapping("/")
	ResponseEntity<OrderTag> addOrderTag(@RequestBody
							 @Parameter(name = "Tag", description = "Received tag")
							 OrderTag orderTag) {
		ResponseEntity<OrderTag> orderTagResponseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		String shortName = orderTag.getAlias().getShortName();
		if(!aliasRepository.existsByShortName(shortName)) {
			log.info("Alias #{} doesn't exist", orderTag.getAlias());
			return orderTagResponseEntity;
		}
		orderTag.setAlias(aliasRepository.findByShortName(shortName));
		log.info("Order tag #{} will be soon created",orderTag);
		return Optional.of(orderTagRepository.save(orderTag))
			.map(ResponseEntity::ok)
			.orElseGet(() -> orderTagResponseEntity);
	}
}
