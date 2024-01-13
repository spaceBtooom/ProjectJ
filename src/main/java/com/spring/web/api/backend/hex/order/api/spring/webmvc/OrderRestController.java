package com.spring.web.api.backend.hex.order.api.spring.webmvc;

import com.spring.web.api.backend.hex.order.api.OrderApi;
import com.spring.web.api.backend.hex.order.api.spring.webmvc.dto.OrderRequest;
import com.spring.web.api.backend.hex.order.api.spring.webmvc.dto.OrderResponse;
import com.spring.web.api.backend.hex.order.api.spring.webmvc.mapper.OrderMapper.GenericMapper.OrderGenericMapper;
import com.spring.web.api.backend.hex.tag.api.spring.webmvc.dto.TagRequest;
import com.spring.web.api.backend.hex.tag.api.spring.webmvc.mapper.GenericMapper.TagGenericMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/order")
@Tag(name = "Order API")
@Log4j2
public class OrderRestController {
	private final OrderApi orderApi;
	private final OrderGenericMapper orderMapper;
	private final TagGenericMapper tagMapper;

	public OrderRestController(OrderApi orderApi,
					   OrderGenericMapper orderMapper,
					   TagGenericMapper tagMapper) {
		this.orderApi = orderApi;
		this.orderMapper = orderMapper;
		this.tagMapper = tagMapper;
	}

	@ApiResponses(value = {
		@ApiResponse(responseCode = "202", description = "Successfully retrieved"),
		@ApiResponse(responseCode = "404", description = "Bad request - No such tags",content = @Content(schema = @Schema(implementation = Void.class))),
		@ApiResponse(responseCode = "422", description = "Unprocessable entity - Order is not saved",content = @Content(schema = @Schema(implementation = Void.class)))
	})
	@Operation(summary = "Save an order ", description = "Return created order")
	@PostMapping("/")
	ResponseEntity<OrderResponse> addOrder(@RequestBody
							   @Parameter(name = "order", description = "Received order")
										   OrderRequest orderRequest) throws Exception {
		return orderApi.save(orderMapper.toDomain(orderRequest))
			.map(order ->
				ResponseEntity
					.ok(orderMapper.toResponse(order)))
			.orElseGet(() -> new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY));
	}

	@Operation(summary = "Get a order by id", description = "Returns a order as per the id")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "202", description = "Successfully retrieved"),
		@ApiResponse(responseCode = "404", description = "Not found - The order was not found")
	})
	@GetMapping("/{id}")
	ResponseEntity<OrderResponse> getOrder(@PathVariable("id")
							   @Parameter(name = "id", description = "Order id", example = "1")
							   UUID id) {
		return orderApi.findById(id)
			.map(order ->
				ResponseEntity.ok(orderMapper.toResponse(order)))
			.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@DeleteMapping("/{id}")
	ResponseEntity<?> deleteOrder(@PathVariable("id")
						@Parameter(name = "id", description = "Order id", example = "f5e7ded8-c281-42bf-bfc1-6a5d44ba6765")
						UUID id) {
		orderApi.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Operation(summary = "Get all orders", description = "Returns all orders")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Successfully retrieved")
	})
	@GetMapping("/")
	List<OrderResponse> getOrders() {
		ArrayList<OrderResponse> orders = new ArrayList<>();
		orderApi
			.findAll()
			.forEach(order -> orders.add(orderMapper.toResponse(order)));
		return orders;
	}

	@Operation(summary = "Add tags to order", description = "Returns successfully added tags")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Successfully retrieved")
	})
	@PostMapping("/tag/{id}")
	ResponseEntity<?> addTags(@PathVariable("id")
					  @Parameter(name = "id", description = "Order id", example = "f5e7ded8-c281-42bf-bfc1-6a5d44ba6765")
					  UUID id,
					  @RequestBody
					  List<TagRequest> tagRequests) {
		if (tagRequests.isEmpty()) {
			return new ResponseEntity<>("There are no tags to add", HttpStatus.NO_CONTENT);
		}
		orderApi.addTags(id, tagRequests.stream().map(tagMapper::toDomain).toList());
		return null;
	}
}
