package org.jobboard.adapter.in.rest.attachedFile.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record AttachedOrderFileResponse(
	@Schema(example = "input.txt")
	String filename,
	@Schema(example = "/api/order/file/d9f4da5f-1e82-4921-a7e6-9f66fc9f9876/UpoBbCRi")
	String downloadUrl) {
}
