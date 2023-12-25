package com.spring.web.api.backend.hex.domain.order.api.spring.webmvc;

import com.spring.web.api.backend.hex.domain.order.Order;
import com.spring.web.api.backend.hex.domain.order.api.OrderApi;
import com.spring.web.api.backend.hex.domain.order.api.spring.webmvc.dto.OrderRequest;
import com.spring.web.api.backend.hex.domain.order.api.spring.webmvc.dto.OrderResponse;
import com.spring.web.api.backend.hex.domain.tag.api.TagApi;
import com.spring.web.api.backend.hex.domain.tag.api.spring.webmvc.dto.TagResponse;
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

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
@Tag(name = "Order API")
@Log4j2
public class OrderRestController {
	private final OrderApi orderApi;
	private final TagApi tagApi;

	public OrderRestController(OrderApi orderApi,
					   TagApi tagApi) {
		this.orderApi = orderApi;
		this.tagApi = tagApi;
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
		return Optional.of(orderApi.save(new Order(orderRequest.tags().stream().map(tag -> tagApi.findByName(tag.name())).collect(Collectors.toList()), orderRequest.title(), orderRequest.comment(),
				orderRequest.price(), orderRequest.urlSource(), orderRequest.expireAt())))
			.map(order -> ResponseEntity
				.ok(new OrderResponse(order.getId(),
					order.getTitle(),
					order.getComment(),
					order.getPrice(),
					order.getUrlSource(),
					order.getExpireAt(),
					order.getTags().stream().map(tag->new TagResponse(tag.getName(), tag.getAliasId())).collect(Collectors.toList()),
					order.getFiles(),
					order.getCreateAt(),
					order.getUpdateAt())))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}

}
