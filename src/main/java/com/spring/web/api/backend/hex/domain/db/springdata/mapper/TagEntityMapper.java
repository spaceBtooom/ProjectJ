package com.spring.web.api.backend.hex.domain.db.springdata.mapper;

import com.spring.web.api.backend.hex.domain.db.springdata.dbo.TagEntity;
import com.spring.web.api.backend.hex.domain.tag.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TagEntityMapper {
	Tag toDomain(TagEntity tagEntity);
	TagEntity toDbo(Tag tag);
}
