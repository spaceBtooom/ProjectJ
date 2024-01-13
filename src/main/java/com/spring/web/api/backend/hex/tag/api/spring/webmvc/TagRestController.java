package com.spring.web.api.backend.hex.tag.api.spring.webmvc;

import com.spring.web.api.backend.hex.tag.api.TagApi;
import com.spring.web.api.backend.hex.tag.api.spring.webmvc.dto.TagRequest;
import com.spring.web.api.backend.hex.tag.api.spring.webmvc.dto.TagResponse;
import com.spring.web.api.backend.hex.tag.api.spring.webmvc.mapper.GenericMapper.TagGenericMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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
		@ApiResponse(responseCode = "200", description = "Successfully retrieved")
	})
	@GetMapping("/")
	ResponseEntity<?> getAllTags() {
		ArrayList<TagResponse> tags = new ArrayList<>();
		tagApi
			.findAll()
			.forEach(tag -> tags.add(new TagResponse(tag.getName(), tag.getAliasId())));
		return ResponseEntity.ok(tags);
	}

	@PostMapping("/")
	ResponseEntity<?> addTag(@RequestBody
					   List<TagRequest> tagRequestList) {
		List<TagResponse> tagResponses= new ArrayList<>();
		Optional.ofNullable(tagRequestList)
			.stream()
			.flatMap(Collection::stream)
			.forEach((tagRequest)->{
				tagResponses.add(
					mapper.toResponse(
						tagApi.save(
							mapper.toDomain(tagRequest))
							.orElseThrow(()->new RuntimeException("Tag can be saved"))));
			});
		return ResponseEntity.ok(tagResponses);
	}


}
