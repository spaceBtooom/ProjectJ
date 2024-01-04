package com.spring.web.api.backend.hex.domain.orderFile.api.spring.webmvc.dto;

import java.util.UUID;

public record OrderFileRequest(String filename,
					 UUID orderId) {
}
