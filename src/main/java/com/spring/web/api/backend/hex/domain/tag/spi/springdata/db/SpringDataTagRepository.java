package com.spring.web.api.backend.hex.domain.tag.spi.springdata.db;

import com.spring.web.api.backend.hex.domain.tag.spi.springdata.dbo.TagEntity;
import com.spring.web.api.backend.hex.domain.tag.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpringDataTagRepository extends CrudRepository<TagEntity, UUID> {
	TagEntity findByName(String name);
	boolean existsByNameAndAliasId(String name, Integer aliasId);
}
