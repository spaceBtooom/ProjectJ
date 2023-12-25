package com.spring.web.api.backend.hex.domain.tag.api.spring.webmvc;

import com.spring.web.api.backend.hex.domain.tag.api.TagApi;
import com.spring.web.api.backend.hex.domain.tag.api.spring.webmvc.dto.TagResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/order-tags")
@Tag(name = "Admin setting: Order tags API")
@Log4j2
public class TagRestController {
	private final TagApi tagApi;

    public TagRestController(TagApi tagApi) {
        this.tagApi = tagApi;
    }

	@Operation(summary = "Get all tags", description = "Returns all tags")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Successfully retrieved")
	})
	@GetMapping("/")
	List<TagResponse> getAllTags() {
		ArrayList<TagResponse> tags = new ArrayList<>();
		tagApi
			.findAll()
			.forEach(tag -> tags.add(new TagResponse(tag.getName(), tag.getAliasId())));
		return tags;
	}
}
