package com.spring.web.api.backend.order;

import com.spring.web.api.backend.fileUtils.FileUploadResponse;
import com.spring.web.api.backend.fileUtils.constraint.FileFormatConstraint;
import com.spring.web.api.backend.fileUtils.constraint.MaxAttachedFileSizeConstraint;
import com.spring.web.api.backend.order.file.OrderAttachedFile;
import com.spring.web.api.backend.order.file.OrderAttachedFileRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("/order")
@Tag(name = "Order API")
@Log4j2
public class OrderRestController {
	private final OrderRepository orderRepository;
	private final OrderFileService fileService;
	private final OrderAttachedFileRepository orderAttachedFileRepository;


	public OrderRestController(OrderRepository orderRepository,
					   OrderFileService fileService,
					   OrderAttachedFileRepository orderAttachedFileRepository) {
		this.orderRepository = orderRepository;
		this.fileService = fileService;
		this.orderAttachedFileRepository = orderAttachedFileRepository;
	}

	@Operation(summary = "Get all orders", description = "Returns all orders")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Successfully retrieved")
	})
	@GetMapping("/")
	List<Order> getOrders() {
		ArrayList<Order> orders = new ArrayList<>();
		orderRepository
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
		Optional<Order> orders = orderRepository.findById(id);
		return orders
			.map(ResponseEntity::ok)
			.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@DeleteMapping("/{id}")
	ResponseEntity<Order> deleteOrder(@PathVariable("id")
						    @Parameter(name = "id", description = "Order id", example = "1")
						    Long id) {
		orderRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
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
		return Optional.of(orderRepository.save(order))
			.map(ResponseEntity::ok)
			.orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}

	@PostMapping("/file")
	ResponseEntity<?> uploadFileForOrder(
		@RequestParam("id")
		Integer id,
		@RequestParam("file")
		@MaxAttachedFileSizeConstraint
		List<@FileFormatConstraint MultipartFile> multipartFile
	) throws IOException, ConstraintViolationException{
		Optional<Order> order = orderRepository.findById(id.longValue());
		if(multipartFile.isEmpty()){
			return new ResponseEntity<>("There are no files to add", HttpStatus.NO_CONTENT);
		}
		if (order.isEmpty()) {
			return new ResponseEntity<>("Order doesn't exist", HttpStatus.NOT_FOUND);
		}

		//How many attached files can be stored on server
		int maxFilesNumber = 3;
		int currentlyFilesNumber = orderAttachedFileRepository.countByOrderId(id);

		if(currentlyFilesNumber >= maxFilesNumber){
			return new ResponseEntity<>("This order currently full of files", HttpStatus.NOT_FOUND);
		}

		int uploadFilesNumber = multipartFile.size();

		if(uploadFilesNumber > maxFilesNumber-currentlyFilesNumber){
			return new ResponseEntity<>("To many attached files. Delete " + (uploadFilesNumber-(maxFilesNumber-currentlyFilesNumber)) + " files", HttpStatus.BAD_REQUEST);
		}


		List<FileUploadResponse> fileUploadResponses = new ArrayList<>();
		for (MultipartFile file : multipartFile) {

			String originalFilename = file.getOriginalFilename();
			String fileName = StringUtils.cleanPath(originalFilename);
			long size = file.getSize();

			String fileCode = fileService.fileUpload(id, fileName, file);

			orderAttachedFileRepository.save(OrderAttachedFile
				.builder()
				.filecode(fileCode)
				.filename(fileName)
				.order(order.get())
				.build());

			fileUploadResponses.add(FileUploadResponse
				.builder()
				.fileName(fileName)
				.size(size)
				.downloadUri("/api/order/file/" + id + "/" + fileCode)
				.build());
		}


		return new ResponseEntity<>(fileUploadResponses,
			HttpStatus.OK);


	}

	@GetMapping("/file/{id}/{filecode}")
	ResponseEntity<?> getFile(@PathVariable("id")
					  @Parameter(name = "id", description = "Order id", example = "1")
					  Integer id,
					  @PathVariable(name = "filecode")
					  String filecode) {
		Resource resource = null;
		try {
			resource = fileService.getFileAsResource(id, filecode);
		} catch (IOException e) {
			return ResponseEntity.internalServerError().build();
		}

		if (resource == null) {
			return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
		}

		//Or ElseGet
		Optional<OrderAttachedFile> orderAttachedFile = orderAttachedFileRepository.findByFilecode(filecode);
		if (orderAttachedFile.isEmpty()) {
			return new ResponseEntity<>("Cannot find filename", HttpStatus.NOT_FOUND);
		}

		String contentType = "application/octet-stream";
		String headerValue = "attachment; filename=\"" + orderAttachedFile.get().getFilename() + "\"";

		return ResponseEntity.ok()
			.contentType(MediaType.parseMediaType(contentType))
			.header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
			.body(resource);
	}

	@GetMapping("/file/{id}")
	ResponseEntity<?> getFiles(@PathVariable("id")
				    @Parameter(name = "id", description = "Order id", example = "1")
				    Long id) {
		List<OrderAttachedFile> filenames = orderAttachedFileRepository.findByOrderId(id);
		if(filenames.isEmpty()){
			return new ResponseEntity<>("There is no files of order with id:"+id, HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(
			filenames
				.stream()
				.map(OrderAttachedFile::getFilename)
				.collect(Collectors.toList()),
			HttpStatus.OK);
	}

}
