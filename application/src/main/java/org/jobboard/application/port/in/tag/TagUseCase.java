package org.jobboard.application.port.in.tag;


import org.jobboard.application.service.tag.exception.TagException;
import org.jobboard.domain.tag.Alias;
import org.jobboard.domain.tag.Tag;

import java.util.List;
import java.util.Optional;

public interface TagUseCase {

	Optional<Tag> save(Tag tag);
	Optional<Tag> update(Tag tag) throws TagException;
	long deleteByName(String name);
	Optional<Tag> findByName(String name);

	List<Tag> findAll();
	boolean existsByName(String name);
	boolean existsByNameAndAliasId(String name, Alias alias);

	List<Tag> saveAll(List<Tag> list);
}
