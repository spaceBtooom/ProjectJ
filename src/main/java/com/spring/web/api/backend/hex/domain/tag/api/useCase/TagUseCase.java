package com.spring.web.api.backend.hex.domain.tag.api.useCase;

import com.spring.web.api.backend.hex.domain.tag.spi.TagSpi;
import com.spring.web.api.backend.hex.domain.tag.Tag;
import com.spring.web.api.backend.hex.domain.tag.api.TagApi;

import java.util.List;
import java.util.UUID;

public class TagUseCase implements TagApi {

	private final TagSpi tagSpi;

	public TagUseCase(TagSpi tagSpi) {
		this.tagSpi = tagSpi;
	}

	@Override
	public void save(Tag tag) {
		tagSpi.save(tag);
	}

	@Override
	public void update(UUID id, Tag tag) {
		tagSpi.save(tag);
	}

	@Override
	public void deleteById(UUID id) {
		tagSpi.deleteById(id);
	}

	@Override
	public Tag findByName(String name) {
		return null;
	}

	@Override
	public List<Tag> findAll() {
		return tagSpi.findAll();
	}

	@Override
	public boolean existsByNameAndAliasId(String name, Integer aliasId) {
		return tagSpi.existsByNameAndAliasId(name, aliasId);
	}
}
