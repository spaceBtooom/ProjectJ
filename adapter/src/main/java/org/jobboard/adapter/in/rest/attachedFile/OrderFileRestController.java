package org.jobboard.adapter.in.rest.attachedFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.jobboard.adapter.in.rest.attachedFile.constraint.*;
import org.jobboard.adapter.in.rest.attachedFile.dto.AttachedOrderFileResponse;

import org.jobboard.adapter.in.rest.attachedFile.mapper.OrderFileGenericMapper;
import org.jobboard.application.port.in.order.OrderUseCase;
import org.jobboard.application.port.in.orderFile.AttachedOrderFileUseCase;
import org.jobboard.application.service.order.exeptions.OrderException;
import org.jobboard.application.service.orderFile.exception.OrderFileException;
import org.jobboard.domain.attachedFile.AttachedOrderFile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/order/file")
@Tag(name = "Order file API")
public class OrderFileRestController {
	private final OrderUseCase orderUseCase;
	private final AttachedOrderFileUseCase attachedOrderFileUseCase;
	private final OrderFileGenericMapper mapper;

	public OrderFileRestController(OrderUseCase orderUseCase,
				       AttachedOrderFileUseCase attachedOrderFileUseCase,
				       OrderFileGenericMapper mapper) {
		this.orderUseCase = orderUseCase;
		this.attachedOrderFileUseCase = attachedOrderFileUseCase;
		this.mapper = mapper;
	}

	@Operation(summary = "Upload files",
		description = "Return name and url by which a file can be downloaded")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Files are successful received"),
		@ApiResponse(responseCode = "400", description = "To many files attached to request",
			content = @Content(schema = @Schema(implementation = Void.class))),
		@ApiResponse(responseCode = "406", description = "This order full of files",
			content = @Content(schema = @Schema(implementation = Void.class))),
		@ApiResponse(responseCode = "412", description = "There are no correct files to add",
			content = @Content(schema = @Schema(implementation = Void.class))),
		@ApiResponse(responseCode = "422", description = "There is no such order",
			content = @Content(schema = @Schema(implementation = Void.class))),
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
		int currentlyFilesNumber = attachedOrderFileUseCase.countByOrderId(id);

		if (currentlyFilesNumber >= maxFilesNumber) {
			return new ResponseEntity<>("This order currently full of files", HttpStatus.NOT_ACCEPTABLE);
		}

		int uploadFilesNumber = multipartFile.size();

		if (uploadFilesNumber > maxFilesNumber - currentlyFilesNumber) {
			return new ResponseEntity<>("To many attached files. Delete " + (uploadFilesNumber - (maxFilesNumber - currentlyFilesNumber)) + " files", HttpStatus.BAD_REQUEST);
		}
		List<AttachedOrderFileResponse> fileUploadResponses = new ArrayList<>();
		for (MultipartFile file : multipartFile) {

			try {
				attachedOrderFileUseCase
					.fileUpload(id, file.getName(), file.getInputStream())
					.ifPresent(orderFile ->
						fileUploadResponses
							.add(mapper.toResponse(orderFile)));
			} catch (IOException e) {
				return new ResponseEntity<>(fileUploadResponses, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity<>(fileUploadResponses, HttpStatus.OK);
	}

	@Operation(summary = "Get files list by order id", description = "Return url by which a file available")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Files' list is successful obtained", content = @Content(schema = @Schema(implementation = AttachedOrderFileResponse.class))),
		@ApiResponse(responseCode = "404", description = "File not found", content = @Content(schema = @Schema(implementation = Void.class))),
		@ApiResponse(responseCode = "422", description = "There is no such order", content = @Content(schema = @Schema(implementation = Void.class))),
		@ApiResponse(responseCode = "500", description = "There is an internal error on the server, corresponding IOException ", content = @Content(schema = @Schema(implementation = Void.class)))
	})
	@GetMapping("/{id}")
	ResponseEntity<?> getFiles(@PathVariable("id")
				   @Parameter(name = "id", description = "Order id", example = "7f659921-adeb-4602-bfe6-82beb3f48725")
				   UUID id)
		throws OrderException {
		List<AttachedOrderFileResponse> filenames = attachedOrderFileUseCase.findByOrderId(id).stream().map(mapper::toResponse).toList();
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
	@GetMapping("/{id}/{filename}")
	ResponseEntity<?> downloadFile(@PathVariable("id")
				       @Parameter(description = "Order id", example = "7f659921-adeb-4602-bfe6-82beb3f48725")
				       UUID id,
				       @Parameter(description = "Name of file", example = "UpoBbCRi")
				       @PathVariable("filename")
				       String filename) throws
		OrderFileException,
		OrderException {
		Resource resource = null;
		try {
			resource = new UrlResource(attachedOrderFileUseCase.getFileAsResource(id, filename));
		} catch (MalformedURLException e) {
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
	ResponseEntity<?> deleteFileByfilecode(@PathVariable("id")
					       @Parameter(description = "Order id", example = "7f659921-adeb-4602-bfe6-82beb3f48725")
					       UUID id,
					       @PathVariable(name = "filecode")
					       @Parameter(description = "filecode that represented by randomize string(8)", example = "UpoBbCRi")
					       String filecode)
		throws OrderException, OrderFileException {

		return ResponseEntity.ok(
			attachedOrderFileUseCase.deleteByOrderIdAndfilecode(id, filecode) == 1
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
		OrderFileException {

		return ResponseEntity.ok(
			attachedOrderFileUseCase.deleteByOrderId(id) > 0
				? "Files was deleted" : "There is no such order or order is empty"
		);

	}
}
