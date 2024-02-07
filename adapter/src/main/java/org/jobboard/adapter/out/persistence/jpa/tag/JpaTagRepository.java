package org.jobboard.adapter.out.persistence.jpa.tag;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaTagRepository extends CrudRepository<TagEntity, UUID> {
	TagEntity findByName(String name);

	long deleteByName(String name);
	boolean existsByName(String name);
	boolean existsByNameAndAliasName(String name, String aliasName);
}
