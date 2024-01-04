package com.spring.web.api.backend.hex.domain.orderFile;

import java.util.UUID;

public class OrderFile {
	private UUID id;
	private String filecode;
	private String filename;
	private UUID orderId;

	public OrderFile(String filecode, String filename, UUID orderId) {
		this.id=UUID.randomUUID();
		this.filecode = filecode;
		this.filename = filename;
		this.orderId = orderId;
	}

	public OrderFile(UUID id, String filecode, String filename, UUID orderId) {
		this.id = id;
		this.filecode = filecode;
		this.filename = filename;
		this.orderId = orderId;
	}

	public UUID getId() {
		return id;
	}

	public String getFilecode() {
		return filecode;
	}

	public String getFilename() {
		return filename;
	}

	public UUID getOrderId() {
		return orderId;
	}

	public void setFilecode(String filecode) {
		this.filecode = filecode;
	}
}
