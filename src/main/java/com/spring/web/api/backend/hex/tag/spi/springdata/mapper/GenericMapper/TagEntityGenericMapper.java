package com.spring.web.api.backend.hex.tag.spi.springdata.mapper.GenericMapper;

import com.spring.web.api.backend.GenericMapperDE;
import com.spring.web.api.backend.hex.tag.domain.Tag;
import com.spring.web.api.backend.hex.tag.spi.springdata.dbo.TagEntity;
import org.springframework.stereotype.Service;

@Service
public class TagEntityGenericMapper implements GenericMapperDE<Tag, TagEntity> {
	@Override
	public TagEntity toDbo(Tag tag) {
		if(tag == null){
			return null;
		}
		return new TagEntity(tag.getId(),
			tag.getName(),
			tag.getAliasName());
	}

	@Override
	public Tag toDomain(TagEntity tagEntity) {
		if(tagEntity == null){
			return null;
		}
		return new Tag(tagEntity.getId(),
			tagEntity.getName(),
			tagEntity.getAliasName());
	}
}
