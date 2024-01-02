package com.spring.web.api.backend.hex.domain.tag.spi.springdata.mapper;

import com.spring.web.api.backend.hex.domain.tag.spi.springdata.dbo.TagEntity;
import com.spring.web.api.backend.hex.domain.tag.Tag;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagEntityMapper {
	Tag toDomain(TagEntity tagEntity);
	TagEntity toDbo(Tag tag);
}
