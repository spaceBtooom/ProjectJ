package com.spring.web.api.backend.hex.domain.tag;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Tag {
	private UUID id;
	private String name;
	private Integer aliasId;

	public Tag(UUID id, String name, Integer aliasId) {
		this.id = id;
		this.name = name;
		this.aliasId = aliasId;
	}
}
