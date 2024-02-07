package org.jobboard.adapter.in.rest.attachedFile.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public record AttachedOrderFileRequest(
	@Schema(example = "input.txt")
	String filename,
	@Schema(example = "d9f4da5f-1e82-4921-a7e6-9f66fc9f9876")
	UUID orderId) {
}
