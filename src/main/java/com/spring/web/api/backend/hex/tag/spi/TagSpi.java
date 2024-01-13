package com.spring.web.api.backend.hex.tag.spi;

import com.spring.web.api.backend.hex.tag.domain.Tag;

import java.util.List;
import java.util.UUID;

public interface TagSpi {

	void save(Tag tag);
	void update(UUID id, Tag tag);
	void deleteById(UUID id);
	Tag findByName(String name);
	List<Tag> findAll();

	boolean existsByNameAndAliasId(String name, Integer aliasId);

}
