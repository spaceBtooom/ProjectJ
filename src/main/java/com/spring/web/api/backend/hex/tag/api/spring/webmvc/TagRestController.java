package com.spring.web.api.backend.hex.tag.api.spring.webmvc;

import com.spring.web.api.backend.hex.tag.api.TagApi;
import com.spring.web.api.backend.hex.tag.api.exception.TagException;
import com.spring.web.api.backend.hex.tag.api.spring.webmvc.dto.TagRequest;
import com.spring.web.api.backend.hex.tag.api.spring.webmvc.dto.TagResponse;
import com.spring.web.api.backend.hex.tag.api.spring.webmvc.mapper.GenericMapper.TagGenericMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Validated
@RestController
@RequestMapping("/admin/tag")
@Tag(name = "Admin setting: Order tags API")
@Log4j2
public class TagRestController {
	private final TagApi tagApi;
	private final TagGenericMapper mapper;

	public TagRestController(TagApi tagApi, TagGenericMapper mapper) {
		this.tagApi = tagApi;
		this.mapper = mapper;
	}

	@Operation(summary = "Get all tags", description = "Returns all tags")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Successfully retrieved", content = @Content(schema = @Schema(implementation = TagResponse.class)))
	})
	@GetMapping("/")
	ResponseEntity<?> getAllTags() {
		ArrayList<TagResponse> tags = new ArrayList<>();
		tagApi
			.findAll()
			.forEach(tag -> tags.add(new TagResponse(tag.getName(), tag.getAliasName())));
		return ResponseEntity.ok(tags);
	}

	@Operation(summary = "Add list of tags", description = "Returns all added tags")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Successfully retrieved", content = @Content(schema = @Schema(implementation = TagResponse.class))),
		@ApiResponse(responseCode = "500", description = "Cannot save tag into the DB", content = @Content(schema = @Schema()))

	})
	@PostMapping("/")
	ResponseEntity<?> addTags(@RequestBody
				  @NotEmpty
				  List<@Valid TagRequest> tagRequestList) {
		return ResponseEntity.ok(tagApi.saveAll(tagRequestList
			.stream()
			.map(mapper::toDomain)
			.toList()));
	}
	@Operation(summary = "Add list of tags", description = "Returns all added tags")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Successfully retrieved", content = @Content(schema = @Schema(implementation = TagResponse.class))),
		@ApiResponse(responseCode = "500", description = "Cannot save tag into the DB", content = @Content(schema = @Schema()))

	})
	@PatchMapping("/")
	ResponseEntity<?> updateTag(@RequestBody
				    @Valid
				    TagRequest tagRequest) throws TagException {
		return ResponseEntity.ok(tagApi.update(mapper.toDomain(tagRequest))
			.orElseThrow(()->new RuntimeException("Tag Rest Controller: updateTag")));
	}

	@DeleteMapping("/")
	ResponseEntity<?> deleteTagByName(@RequestParam
					  @NotBlank
					  String name){
		return ResponseEntity.ok(tagApi.deleteByName(name) == 1
			? "Tag successfully deleted"
			: "There is no such tag");
	}

}
