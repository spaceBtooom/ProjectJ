package com.spring.web.api.backend.hex.tag.spi.springdata.spi;

import com.spring.web.api.backend.hex.tag.api.exception.TagCannotBeSaveException;
import com.spring.web.api.backend.hex.tag.spi.springdata.db.SpringDataTagRepository;
import com.spring.web.api.backend.hex.tag.spi.springdata.mapper.GenericMapper.TagEntityGenericMapper;
import com.spring.web.api.backend.hex.tag.domain.Tag;
import com.spring.web.api.backend.hex.tag.spi.TagSpi;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TagDatabaseAdapter implements TagSpi {


	private final SpringDataTagRepository tagRepository;

	private final TagEntityGenericMapper tagMapper;

	public TagDatabaseAdapter(SpringDataTagRepository tagRepository, TagEntityGenericMapper tagMapper) {
		this.tagRepository = tagRepository;
		this.tagMapper = tagMapper;
	}


	@Override
	public Optional<Tag> save(Tag tag) {
		if(tagRepository.existsByName(tag.getName())){
			return Optional.empty();
		}
		return Optional.of(tagMapper.toDomain(tagRepository
			.save(tagMapper.toDbo(tag))));
	}
	@Override
	public void update(UUID id, Tag tag) {
		tagRepository.save(tagMapper.toDbo(tag));
	}

	@Override
	public void deleteById(UUID id) {
		tagRepository.deleteById(id);
	}

	@Override
	public Tag findByName(String name) {
		return tagMapper.toDomain(tagRepository.findByName(name));
	}

	@Override
	public List<Tag> findAll() {
		List<Tag> tags = new ArrayList<>();
		tagRepository.findAll().forEach(tagEntity -> tags.add(tagMapper.toDomain(tagEntity)));
		return tags;
	}

	@Override
	public boolean existsByNameAndAliasId(String name, String aliasName) {
		return tagRepository.existsByNameAndAliasName(name, aliasName);
	}
	@Transactional
	@Override
	public List<Tag> saveAll(List<Tag> tags) {
		List<Tag> tagList = new ArrayList<>();
		tags
			.stream()
			.forEach(tag->{
				save(tag).ifPresentOrElse(tagList::add,()->{
					throw new TagCannotBeSaveException(
						"This tag: \n"
						+ tag.toString()
						+"\nIs not valid");
				});
			});
		return tagList;
	}
}
