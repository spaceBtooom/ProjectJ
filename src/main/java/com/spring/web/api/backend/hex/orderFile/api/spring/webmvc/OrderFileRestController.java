package com.spring.web.api.backend.hex.orderFile.api.spring.webmvc;

import com.spring.web.api.backend.hex.order.api.OrderApi;
import com.spring.web.api.backend.hex.order.api.exeptions.OrderException;
import com.spring.web.api.backend.hex.orderFile.api.OrderFileApi;
import com.spring.web.api.backend.hex.orderFile.api.exception.OrderFileException;
import com.spring.web.api.backend.hex.orderFile.api.spring.webmvc.constraint.FileFormatConstraint;
import com.spring.web.api.backend.hex.orderFile.api.spring.webmvc.constraint.MaxAttachedFileSizeConstraint;
import com.spring.web.api.backend.hex.orderFile.api.spring.webmvc.dto.OrderFileResponse;
import com.spring.web.api.backend.hex.orderFile.api.spring.webmvc.mapper.GenericMapper.OrderFileGenericMapper;
import com.spring.web.api.backend.hex.order.api.exeptions.OrderIdNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/order/file")
@Tag(name = "Order file API")
public class OrderFileRestController {
	private final OrderApi orderApi;
	private final OrderFileApi orderFileApi;
	private final OrderFileGenericMapper mapper;

	public OrderFileRestController(OrderApi orderApi, OrderFileApi orderFileApi, OrderFileGenericMapper mapper) {
		this.orderApi = orderApi;
		this.orderFileApi = orderFileApi;
		this.mapper = mapper;
	}

	@Operation(summary = "Upload files", description = "Return name and url by which a file can be downloaded")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Files are successful received"),
		@ApiResponse(responseCode = "400", description = "To many files attached to request", content = @Content(schema = @Schema(implementation = Void.class))),
		@ApiResponse(responseCode = "406", description = "This order full of files", content = @Content(schema = @Schema(implementation = Void.class))),
		@ApiResponse(responseCode = "412", description = "There are no correct files to add", content = @Content(schema = @Schema(implementation = Void.class))),
		@ApiResponse(responseCode = "422", description = "There is no such order", content = @Content(schema = @Schema(implementation = Void.class))),
		@ApiResponse(responseCode = "500", description = "There is an internal error on the server, corresponding IOException ", content = @Content(schema = @Schema(implementation = Void.class)))
	})
	@PostMapping("")
	ResponseEntity<?> uploadFileForOrder(@RequestParam("id")
					     UUID id,
					     @RequestParam("file") @MaxAttachedFileSizeConstraint
					     List<@FileFormatConstraint MultipartFile> multipartFile)
		throws OrderFileException, OrderException {
		if (multipartFile.isEmpty()) {
			return new ResponseEntity<>("There are no files to add", HttpStatus.PRECONDITION_FAILED);
		}

		int maxFilesNumber = 3;
		int currentlyFilesNumber = orderFileApi.countByOrderId(id);

		if (currentlyFilesNumber >= maxFilesNumber) {
			return new ResponseEntity<>("This order currently full of files", HttpStatus.NOT_ACCEPTABLE);
		}

		int uploadFilesNumber = multipartFile.size();

		if (uploadFilesNumber > maxFilesNumber - currentlyFilesNumber) {
			return new ResponseEntity<>("To many attached files. Delete " + (uploadFilesNumber - (maxFilesNumber - currentlyFilesNumber)) + " files", HttpStatus.BAD_REQUEST);
		}
		List<OrderFileResponse> fileUploadResponses = new ArrayList<>();
		for (MultipartFile file : multipartFile) {
			orderFileApi
				.fileUpload(id, file)
				.ifPresent(orderFile ->
					fileUploadResponses
						.add(mapper.toResponse(orderFile)));
		}
		return new ResponseEntity<>(fileUploadResponses, HttpStatus.OK);
	}

	@Operation(summary = "Get files list by order id", description = "Return url by which a file available")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Files' list is successful obtained", content = @Content(schema = @Schema(implementation = OrderFileResponse.class))),
		@ApiResponse(responseCode = "404", description = "File not found", content = @Content(schema = @Schema(implementation = Void.class))),
		@ApiResponse(responseCode = "422", description = "There is no such order", content = @Content(schema = @Schema(implementation = Void.class))),
		@ApiResponse(responseCode = "500", description = "There is an internal error on the server, corresponding IOException ", content = @Content(schema = @Schema(implementation = Void.class)))
	})
	@GetMapping("/{id}")
	ResponseEntity<?> getFiles(@PathVariable("id")
				   @Parameter(name = "id", description = "Order id", example = "7f659921-adeb-4602-bfe6-82beb3f48725")
				   UUID id)
		throws OrderException {
		List<OrderFileResponse> filenames = orderFileApi.findByOrderId(id).stream().map(mapper::toResponse).toList();
		if (filenames.isEmpty()) {
			return new ResponseEntity<>("There is no files of order with id:" + id, HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(
			filenames,
			HttpStatus.OK);
	}


	@Operation(summary = "Download file by order id and filecode", description = "Return file")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Files' list is successful obtained"),
		@ApiResponse(responseCode = "404", description = "File not found", content = @Content(schema = @Schema(implementation = Void.class))),
		@ApiResponse(responseCode = "417", description = "There is no such filecode", content = @Content(schema = @Schema(implementation = Void.class))),
		@ApiResponse(responseCode = "422", description = "There is no such order", content = @Content(schema = @Schema(implementation = Void.class))),
		@ApiResponse(responseCode = "500", description = "There is an internal error on the server, corresponding IOException ", content = @Content(schema = @Schema(implementation = Void.class)))
	})
	@GetMapping("/{id}/{filecode}")
	ResponseEntity<?> downloadFile(@PathVariable("id")
				       @Parameter(description = "Order id", example = "7f659921-adeb-4602-bfe6-82beb3f48725")
				       UUID id,
				       @Parameter(description = "Filecode that represented by randomize string(8)", example = "UpoBbCRi")
				       @PathVariable(name = "filecode")
				       String filecode)
		throws OrderFileException, OrderException {
		//MalformedURLException
		Resource resource = orderFileApi.getFileAsResource(id, filecode);
		if (resource == null) {
			return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
		}

		String contentType = "application/octet-stream";
		String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";

		return ResponseEntity.ok()
			.contentType(MediaType.parseMediaType(contentType))
			.header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
			.body(resource);
	}

	@Operation(summary = "Delete file by order id and filecode")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Return number of deleted files"),
	})

	@DeleteMapping("/{id}/{filecode}")
	ResponseEntity<?> deleteFileByFilecode(@PathVariable("id")
					       @Parameter(description = "Order id", example = "7f659921-adeb-4602-bfe6-82beb3f48725")
					       UUID id,
					       @PathVariable(name = "filecode")
					       @Parameter(description = "Filecode that represented by randomize string(8)", example = "UpoBbCRi")
					       String filecode)
		throws OrderException, OrderFileException {

		return ResponseEntity.ok(
			orderFileApi.deleteByOrderIdAndFilecode(id, filecode) == 1
				? "File was deleted" : "There is no such file or order id");

	}

	@Operation(summary = "Delete files by order id")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Return number of deleted files"),
	})
	@DeleteMapping("/{id}")
	ResponseEntity<?> deleteFilesByOrderId(@PathVariable("id")
					       @Parameter(description = "Order id", example = "7f659921-adeb-4602-bfe6-82beb3f48725")
					       UUID id)
	throws OrderException,
		OrderFileException{

		return ResponseEntity.ok(
			orderFileApi.deleteByOrderId(id) > 0
				? "Files was deleted" : "There is no such order or order is empty"
		);

	}
}
