package org.jobboard.adapter.out.persistence.jpa.tag.mapper;

import org.jobboard.adapter.out.persistence.jpa.common.mapper.GenericMapperDE;
import org.jobboard.adapter.out.persistence.jpa.tag.TagEntity;
import org.jobboard.domain.tag.Alias;
import org.jobboard.domain.tag.Tag;
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
			tag.getAlias().name());
	}

	@Override
	public Tag toDomain(TagEntity tagEntity) {
		if(tagEntity == null){
			return null;
		}
		Tag tag = new Tag(tagEntity.getId());
		tag.setName(tagEntity.getName());
		tag.setAlias(Alias.valueOf(tagEntity.getAliasName()));
		return tag;


	}
}
