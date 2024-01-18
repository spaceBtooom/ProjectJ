package com.spring.web.api.backend.hex.tag.spi.springdata.db;

import com.spring.web.api.backend.hex.tag.spi.springdata.dbo.TagEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpringDataTagRepository extends CrudRepository<TagEntity, UUID> {
	TagEntity findByName(String name);

	long deleteByName(String name);
	boolean existsByName(String name);
	boolean existsByNameAndAliasName(String name, String aliasName);
}
