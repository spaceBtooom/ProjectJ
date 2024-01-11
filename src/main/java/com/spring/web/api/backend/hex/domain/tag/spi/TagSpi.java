package com.spring.web.api.backend.hex.domain.tag.spi;

import com.spring.web.api.backend.hex.domain.tag.Tag;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TagSpi {

	void save(Tag tag);
	void update(UUID id, Tag tag);
	void deleteById(UUID id);
	Tag findByName(String name);
	List<Tag> findAll();

	boolean existsByNameAndAliasId(String name, Integer aliasId);

}
