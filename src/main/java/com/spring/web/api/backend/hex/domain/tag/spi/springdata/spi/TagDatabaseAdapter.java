package com.spring.web.api.backend.hex.domain.tag.spi.springdata.spi;

import com.spring.web.api.backend.hex.domain.tag.spi.springdata.db.SpringDataTagRepository;
import com.spring.web.api.backend.hex.domain.tag.spi.springdata.mapper.GenericMapper.TagEntityGenericMapper;
import com.spring.web.api.backend.hex.domain.tag.Tag;
import com.spring.web.api.backend.hex.domain.tag.spi.TagSpi;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TagDatabaseAdapter implements TagSpi {


	private final SpringDataTagRepository tagRepository;

	private final TagEntityGenericMapper tagMapper;

	public TagDatabaseAdapter(SpringDataTagRepository tagRepository, TagEntityGenericMapper tagMapper) {
		this.tagRepository = tagRepository;
		this.tagMapper = tagMapper;
	}


	@Override
	public void save(Tag tag) {
		tagRepository.save(tagMapper.toDbo(tag));
	}

	@Override
	public void update(UUID id, Tag tag) {
		tagRepository.save(tagMapper.toDbo(tag));
	}

	@Override
	public void deleteById(UUID id) {
		tagRepository.deleteById(id);
	}

	@Override
	public Tag findByName(String name) {
		return tagMapper.toDomain(tagRepository.findByName(name));
	}

	@Override
	public List<Tag> findAll() {
		List<Tag> tags = new ArrayList<>();
		tagRepository.findAll().forEach(tagEntity -> tags.add(tagMapper.toDomain(tagEntity)));
		return tags;
	}
}
