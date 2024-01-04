package com.spring.web.api.backend.hex.domain.order.api.spring.webmvc;

import com.spring.web.api.backend.hex.domain.order.api.OrderApi;
import com.spring.web.api.backend.hex.domain.order.api.spring.webmvc.dto.OrderRequest;
import com.spring.web.api.backend.hex.domain.order.api.spring.webmvc.dto.OrderResponse;
import com.spring.web.api.backend.hex.domain.order.api.spring.webmvc.mapper.OrderMapper.GenericMapper.OrderGenericMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@Tag(name = "Order API")
@Log4j2
public class OrderRestController {
	private final OrderApi orderApi;
	private final OrderGenericMapper orderMapper;

	public OrderRestController(OrderApi orderApi,
					   OrderGenericMapper orderMapper) {
		this.orderApi = orderApi;
		this.orderMapper = orderMapper;
	}

	@ApiResponses(value = {
		@ApiResponse(responseCode = "202", description = "Successfully retrieved"),
		@ApiResponse(responseCode = "400", description = "Bad request - No such order", content = {})
	})
	@Operation(summary = "Save an order ", description = "Return created order")
	@PostMapping("/")
	ResponseEntity<OrderResponse> addOrder(@RequestBody
							   @Parameter(name = "order", description = "Received order")
							   OrderRequest orderRequest) {
		return orderApi.save(orderMapper.toDomain(orderRequest))
			.map(order ->
				ResponseEntity
					.ok(orderMapper.toResponse(order)))
			.orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}
}
