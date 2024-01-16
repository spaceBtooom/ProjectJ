package com.spring.web.api.backend.hex.tag.domain;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Tag {
	private UUID id;
	private String name;
	private String aliasName;

	public Tag(String name, String aliasName) {
		this.id=UUID.randomUUID();
		this.name = name;
		this.aliasName = aliasName;
	}

	public Tag(UUID id, String name, String aliasName) {
		this.id = id;
		this.name = name;
		this.aliasName = aliasName;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}
	@Override
	public String toString() {
		return "Tag{" +
			"name='" + name + '\'' +
			", aliasName=" + aliasName +
			'}';
	}
}
