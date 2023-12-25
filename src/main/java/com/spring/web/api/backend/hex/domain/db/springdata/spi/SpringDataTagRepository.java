package com.spring.web.api.backend.hex.domain.db.springdata.spi;

import com.spring.web.api.backend.hex.domain.db.springdata.dbo.TagEntity;
import com.spring.web.api.backend.hex.domain.tag.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpringDataTagRepository extends CrudRepository<TagEntity, UUID> {
	Tag findByName(String name);
}
