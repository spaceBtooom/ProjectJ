package com.spring.web.api.backend.hex.domain.tag.api;

import com.spring.web.api.backend.hex.domain.tag.Tag;

import java.util.List;
import java.util.UUID;

public interface TagApi {

	void save(Tag tag);
	void update(UUID id, Tag tag);
	void deleteById(UUID id);
	Tag findByName(String name);

	List<Tag> findAll();

}
