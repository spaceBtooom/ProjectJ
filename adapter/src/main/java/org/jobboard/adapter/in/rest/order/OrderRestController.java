package org.jobboard.adapter.in.rest.order;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.extern.log4j.Log4j2;
import org.jobboard.adapter.in.rest.order.dto.OrderRequest;
import org.jobboard.adapter.in.rest.order.dto.OrderResponse;
import org.jobboard.adapter.in.rest.order.mapper.OrderGenericMapper;
import org.jobboard.application.port.in.order.OrderUseCase;
import org.jobboard.application.service.order.exeptions.OrderException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@Validated
@RequestMapping("/order")
@Tag(name = "Order API")
@Log4j2
public class OrderRestController {
	private final OrderUseCase orderUseCase;
	private final OrderGenericMapper orderMapper;

	public OrderRestController(OrderUseCase orderUseCase,
				   OrderGenericMapper orderMapper) {
		this.orderUseCase = orderUseCase;
		this.orderMapper = orderMapper;
	}

	@ApiResponses(value = {
		@ApiResponse(responseCode = "202", description = "Successfully retrieved"),
		@ApiResponse(responseCode = "404", description = "Bad request - No such tags", content = @Content(schema = @Schema(implementation = Void.class))),
		@ApiResponse(responseCode = "500", description = "Order is not saved due to server", content = @Content(schema = @Schema(implementation = Void.class)))
	})
	@Operation(summary = "Save an order", description = "Return created order")
	@PostMapping("/")
	ResponseEntity<?> addOrder(@RequestBody
				   @Parameter(name = "order", description = "Received order")
				   OrderRequest orderRequest) throws OrderException {
		return orderUseCase.save(orderMapper.toDomain(orderRequest))
			.map(order ->
				ResponseEntity
					.ok(orderMapper.toResponse(order)))
			.orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
	}

	@Operation(summary = "Get an order by id", description = "Returns an order as per the id")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Successfully retrieved"),
		@ApiResponse(responseCode = "404", description = "Not found - The order was not found")
	})
	@GetMapping("/{id}")
	ResponseEntity<?> getOrder(@PathVariable("id")
				   @Parameter(description = "Order id", example = "f5e7ded8-c281-42bf-bfc1-6a5d44ba6765")
				   UUID id) {
		return orderUseCase.findById(id)
			.map(order ->
				ResponseEntity.ok(orderMapper.toResponse(order)))
			.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}


	@Operation(summary = "Delete the order by id", description = "Returns successful status")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Successfully deleted",content = @Content(schema = @Schema(implementation = Void.class))),
		@ApiResponse(responseCode = "422", description = "The order is not exist",content = @Content(schema = @Schema(implementation = Void.class)))
	})
	@DeleteMapping("/{id}")
	ResponseEntity<?> deleteOrder(@PathVariable("id")
				      @Parameter(description = "Order id", example = "f5e7ded8-c281-42bf-bfc1-6a5d44ba6765")
				      UUID id)
	throws OrderException {
		orderUseCase.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Operation(summary = "Get all orders", description = "Returns all orders")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Successfully retrieved")
	})
	@GetMapping("/")
	ResponseEntity<?> getOrders() {
		ArrayList<OrderResponse> orders = new ArrayList<>();
		orderUseCase
			.findAll()
			.forEach(order -> orders.add(orderMapper.toResponse(order)));
		return ResponseEntity.ok(orders);
	}

	// check
	@Operation(summary = "Add tags to order", description = "Returns successfully added tags")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Successfully retrieved", content = @Content(schema = @Schema(implementation = ArrayList.class))),
		@ApiResponse(responseCode = "400", description = "Tags' list must not be empty",content = @Content(schema = @Schema(implementation = Void.class)))
	})
	@PostMapping("/tag/{id}")
	ResponseEntity<?> addTags(@PathVariable("id")
				  @Parameter(description = "Order id", example = "f5e7ded8-c281-42bf-bfc1-6a5d44ba6765")
				  UUID id,
				  @Parameter(description = "Tag names", example = "Java, Python, Delay")
				  @NotBlank
				  List<String> tagNames)
	throws OrderException{

		return  ResponseEntity.ok(orderUseCase.addTags(id, tagNames));
	}
}
