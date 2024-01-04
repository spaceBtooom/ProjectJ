package com.spring.web.api.backend.hex.domain.tag.spi.springdata.mapper.GenericMapper;

import com.spring.web.api.backend.GenericMapperDE;
import com.spring.web.api.backend.hex.domain.tag.Tag;
import com.spring.web.api.backend.hex.domain.tag.spi.springdata.dbo.TagEntity;
import org.springframework.stereotype.Service;

@Service
public class TagEntityGenericMapper implements GenericMapperDE<Tag, TagEntity> {
	@Override
	public TagEntity toDbo(Tag tag) {
		return new TagEntity(tag.getId(),
			tag.getName(),
			tag.getAliasId());
	}

	@Override
	public Tag toDomain(TagEntity tagEntity) {
		return new Tag(tagEntity.getId(),
			tagEntity.getName(),
			tagEntity.getAliasId());
	}
}
