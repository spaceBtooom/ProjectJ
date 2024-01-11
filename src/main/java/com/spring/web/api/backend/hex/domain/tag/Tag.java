package com.spring.web.api.backend.hex.domain.tag;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Tag {
	private UUID id;
	private String name;
	private Integer aliasId;

	public Tag(String name, Integer aliasId) {
		this.id=UUID.randomUUID();
		this.name = name;
		this.aliasId = aliasId;
	}

	public Tag(UUID id, String name, Integer aliasId) {
		this.id = id;
		this.name = name;
		this.aliasId = aliasId;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAliasId(Integer aliasId) {
		this.aliasId = aliasId;
	}

	@Override
	public String toString() {
		return "Tag{" +
			"name='" + name + '\'' +
			", aliasId=" + aliasId +
			'}';
	}
}
