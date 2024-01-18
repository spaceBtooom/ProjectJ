package com.spring.web.api.backend.hex.tag.api;

import com.spring.web.api.backend.hex.tag.api.exception.TagException;
import com.spring.web.api.backend.hex.tag.domain.Tag;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TagApi {

	Optional<Tag> save(Tag tag);
	Optional<Tag> update(Tag tag) throws TagException;
	long deleteByName(String name);
	Tag findByName(String name);

	List<Tag> findAll();
	boolean existsByNameAndAliasId(String name, String aliasName);

	List<Tag> saveAll(List<Tag> list);
}
