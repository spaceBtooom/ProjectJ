package com.spring.web.api.backend.hex.domain.tag.api.spring.webmvc.mapper.GenericMapper;

import com.spring.web.api.backend.GenericMapperRDR;
import com.spring.web.api.backend.hex.domain.tag.Tag;
import com.spring.web.api.backend.hex.domain.tag.api.spring.webmvc.dto.TagRequest;
import com.spring.web.api.backend.hex.domain.tag.api.spring.webmvc.dto.TagResponse;
import org.springframework.stereotype.Service;

@Service
public class TagGenericMapper implements GenericMapperRDR<TagRequest, Tag, TagResponse> {
	@Override
	public Tag toDomain(TagRequest tagRequest) {
		if (tagRequest == null) {
			return null;
		}
		return new Tag(tagRequest.name(),
			tagRequest.aliasId());
	}

	@Override
	public TagResponse toResponse(Tag tag) {
		if (tag == null) {
			return null;
		}
		return new TagResponse(tag.getName(),
			tag.getAliasId());
	}
}
