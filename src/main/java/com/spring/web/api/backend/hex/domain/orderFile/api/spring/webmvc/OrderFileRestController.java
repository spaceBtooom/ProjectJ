package com.spring.web.api.backend.hex.domain.orderFile.api.spring.webmvc;

import com.spring.web.api.backend.hex.domain.orderFile.api.OrderFileApi;
import com.spring.web.api.backend.hex.domain.orderFile.api.spring.webmvc.constraint.FileFormatConstraint;
import com.spring.web.api.backend.hex.domain.orderFile.api.spring.webmvc.constraint.MaxAttachedFileSizeConstraint;
import com.spring.web.api.backend.hex.domain.orderFile.api.spring.webmvc.dto.OrderFileResponse;
import com.spring.web.api.backend.hex.domain.orderFile.api.spring.webmvc.mapper.GenericMapper.OrderFileGenericMapper;
import io.swagger.v3.oas.annotations.Parameter;
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
public class OrderFileRestController {
	private final OrderFileApi orderFileApi;
	private final OrderFileGenericMapper mapper;

	public OrderFileRestController(OrderFileApi orderFileApi, OrderFileGenericMapper mapper) {
		this.orderFileApi = orderFileApi;
		this.mapper = mapper;
	}
	@GetMapping("/{id}")
	ResponseEntity<?> getFiles(@PathVariable("id")
					   @Parameter(name = "id", description = "Order id", example = "1")
					   UUID id) {
		List<OrderFileResponse> filenames = orderFileApi.findByOrderId(id).stream().map(mapper::toResponse).toList();
		if(filenames.isEmpty()){
			return new ResponseEntity<>("There is no files of order with id:"+id, HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(
			filenames,
			HttpStatus.OK);
	}

	@PostMapping("")
	ResponseEntity<?> uploadFileForOrder(
		@RequestParam("id")
		UUID id,
		@RequestParam("file")@MaxAttachedFileSizeConstraint
		List<@FileFormatConstraint MultipartFile> multipartFile
	) throws IOException {
		if(multipartFile.isEmpty()){
			return new ResponseEntity<>("There are no files to add", HttpStatus.NO_CONTENT);
		}
		// Validation must be wrapper on OrderFileApi
		//
		//
		//
		int maxFilesNumber = 3;
		int currentlyFilesNumber = orderFileApi.countByOrderId(id);

		if(currentlyFilesNumber >= maxFilesNumber){
			return new ResponseEntity<>("This order currently full of files", HttpStatus.NOT_FOUND);
		}

		int uploadFilesNumber = multipartFile.size();

		if(uploadFilesNumber > maxFilesNumber-currentlyFilesNumber){
			return new ResponseEntity<>("To many attached files. Delete " + (uploadFilesNumber-(maxFilesNumber-currentlyFilesNumber)) + " files", HttpStatus.BAD_REQUEST);
		}
		List<OrderFileResponse> fileUploadResponses = new ArrayList<>();
		for (MultipartFile file : multipartFile) {
			orderFileApi.fileUpload(id, file).ifPresent(orderFile->fileUploadResponses.add(mapper.toResponse(orderFile)));
		}
		return new ResponseEntity<>(fileUploadResponses, HttpStatus.OK);
	}

	@GetMapping("/{id}/{filecode}")
	ResponseEntity<?> downloadFile(@PathVariable("id")
					  @Parameter(name = "id", description = "Order id", example = "1")
					  UUID id,
					  @PathVariable(name = "filecode")
					  String filecode) {
		Resource resource = null;
		try {
			resource = orderFileApi.getFileAsResource(id, filecode);
		} catch (IOException e) {
			return ResponseEntity.internalServerError().build();
		}

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
}
