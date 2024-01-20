package org.jobboard.adapter.in.rest.tag.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.jobboard.domain.tag.Alias;

public record TagResponse(
	@Schema(example = "Java")
	String name,
	@Schema(example = "LANGUAGE")
	Alias alias) {
}
