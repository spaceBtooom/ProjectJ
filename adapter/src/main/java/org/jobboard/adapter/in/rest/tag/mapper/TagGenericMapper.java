package org.jobboard.adapter.in.rest.tag.mapper;

import org.jobboard.adapter.in.rest.common.mappers.GenericMapperRDR;
import org.jobboard.adapter.in.rest.tag.dto.TagRequest;
import org.jobboard.adapter.in.rest.tag.dto.TagResponse;
import org.jobboard.domain.tag.Alias;
import org.jobboard.domain.tag.Tag;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TagGenericMapper implements GenericMapperRDR<TagRequest, Tag, TagResponse> {
	@Override
	public Tag toDomain(TagRequest tagRequest) {
		if (tagRequest == null) {
			return null;
		}
		Tag tag = new Tag(UUID.randomUUID());
		tag.setName(tagRequest.name());
		tag.setAlias(Alias.valueOf(tagRequest.alias()));
		return tag;
	}

	@Override
	public TagResponse toResponse(Tag tag) {
		if (tag == null) {
			return null;
		}
		return new TagResponse(tag.getName(),
			tag.getAlias());
	}
}
