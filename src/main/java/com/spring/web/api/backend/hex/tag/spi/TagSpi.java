package com.spring.web.api.backend.hex.tag.spi;

import com.spring.web.api.backend.hex.tag.domain.Tag;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TagSpi {

	Optional<Tag> save(Tag tag);
	void update(UUID id, Tag tag);
	void deleteById(UUID id);
	Tag findByName(String name);
	List<Tag> findAll();

	boolean existsByNameAndAliasId(String name, String aliasName);

	List<Tag> saveAll(List<Tag> tags);

}
