package org.jobboard.application.port.out.persistence.tag;

import org.jobboard.domain.tag.Alias;
import org.jobboard.domain.tag.Tag;

import java.util.List;
import java.util.Optional;

public interface TagRepository {

	Optional<Tag> save(Tag tag);

	Optional<Tag> update(Tag tag);

	long deleteByName(String name);

	Optional<Tag> findByName(String name);

	List<Tag> findAll();

	boolean existsByNameAndAliasId(String name, Alias alias);

	List<Tag> saveAll(List<Tag> tags);

	boolean existsByName(String name);
}
