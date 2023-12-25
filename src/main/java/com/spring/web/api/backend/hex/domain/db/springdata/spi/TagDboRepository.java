package com.spring.web.api.backend.hex.domain.db.springdata.spi;

import com.spring.web.api.backend.hex.domain.db.springdata.mapper.TagEntityMapper;
import com.spring.web.api.backend.hex.domain.tag.Tag;
import com.spring.web.api.backend.hex.domain.tag.spi.TagSpi;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TagDboRepository implements TagSpi {


	private final SpringDataTagRepository tagRepository;

	private final TagEntityMapper tagMapper;

    public TagDboRepository(SpringDataTagRepository tagRepository, TagEntityMapper tagMapper) {
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
		return tagRepository.findByName(name);
	}

	@Override
	public List<Tag> findAll() {
		List<Tag> tags = new ArrayList<>();
		tagRepository.findAll().forEach(tagEntity -> tags.add(tagMapper.toDomain(tagEntity)));
		return tags;
	}
}
