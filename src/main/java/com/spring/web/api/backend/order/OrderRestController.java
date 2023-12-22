package com.spring.web.api.backend.order;

import com.spring.web.api.backend.fileUtils.FileUploadResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
@Tag(name = "Order API")
public class OrderRestController {
	private final OrderRepository repository;
	private final OrderFileService fileService;

	private static final Integer FILE_SIZE_MAX = 1000000;

	public OrderRestController(OrderRepository repository,
					   OrderFileService fileService) {
		this.repository = repository;
		this.fileService = fileService;
	}

	@Operation(summary = "Get all orders", description = "Returns all orders")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Successfully retrieved")
	})
	@GetMapping("/")
	List<Order> getOrders() {
		ArrayList<Order> orders = new ArrayList<>();
		repository
			.findAll()
			.forEach(orders::add);
		return orders;
	}

	@Operation(summary = "Get a order by id", description = "Returns a order as per the id")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "202", description = "Successfully retrieved"),
		@ApiResponse(responseCode = "404", description = "Not found - The order was not found")
	})
	@GetMapping("/{id}")
	ResponseEntity<Order> getOrder(@PathVariable("id")
						 @Parameter(name = "id", description = "Order id", example = "1")
						 Long id) {
		Optional<Order> orders = repository.findById(id);
		return orders
			.map(ResponseEntity::ok)
			.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@ApiResponses(value = {
		@ApiResponse(responseCode = "202", description = "Successfully retrieved"),
		@ApiResponse(responseCode = "400", description = "Bad request - No such order", content = {})
	})
	@Operation(summary = "Save an order ", description = "Return created order")
	@PostMapping("/")
	ResponseEntity<Order> addOrder(@RequestBody
						 @Parameter(name = "order", description = "Received order")
						 Order order) {
		return Optional.of(repository.save(order))
			.map(ResponseEntity::ok)
			.orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}

	@PostMapping("/file")
	ResponseEntity<List<FileUploadResponse>> uploadFileForOrder(
		@RequestParam("id") Integer id,
		@RequestParam("file") List<MultipartFile> multipartFile
	) throws IOException {
		List<FileUploadResponse> fileUploadResponses = new ArrayList<>();
		for (MultipartFile file : multipartFile) {
			String originalFilename = file.getOriginalFilename();
			String fileName = StringUtils.cleanPath(originalFilename);
			long size = file.getSize();
			String fileCode = fileService.fileUpload(id, fileName, file);
			fileUploadResponses.add(FileUploadResponse
				.builder()
				.fileName(fileName)
				.size(size)
				.downloadUri("/downloadFile/" + fileCode)
				.build());
		}

		return new ResponseEntity<>(fileUploadResponses,
			HttpStatus.OK);

	}
}
