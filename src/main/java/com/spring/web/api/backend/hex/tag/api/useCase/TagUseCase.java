package com.spring.web.api.backend.hex.tag.api.useCase;

import com.spring.web.api.backend.hex.tag.api.exception.TagException;
import com.spring.web.api.backend.hex.tag.spi.TagSpi;
import com.spring.web.api.backend.hex.tag.domain.Tag;
import com.spring.web.api.backend.hex.tag.api.TagApi;

import java.util.List;
import java.util.Optional;

public class TagUseCase implements TagApi {

	private final TagSpi tagSpi;

	public TagUseCase(TagSpi tagSpi) {
		this.tagSpi = tagSpi;
	}

	@Override
	public Optional<Tag> save(Tag tag) {
		return tagSpi.save(tag);
	}

	@Override
	public Optional<Tag> update(Tag tag) throws TagException {
		return tagSpi.update(tag);
	}

	@Override
	public long deleteByName(String name) {
		return tagSpi.deleteByName(name);
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
	public boolean existsByNameAndAliasId(String name, String aliasName) {
		return tagSpi.existsByNameAndAliasId(name, aliasName);
	}

	@Override
	public List<Tag> saveAll(List<Tag> list) {
		return tagSpi.saveAll(list);
	}
}
