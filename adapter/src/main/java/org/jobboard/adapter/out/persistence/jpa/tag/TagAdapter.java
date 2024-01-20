package org.jobboard.adapter.out.persistence.jpa.tag;

import org.jobboard.adapter.out.persistence.jpa.tag.exception.TagEntityCannotBeSavedException;
import org.jobboard.adapter.out.persistence.jpa.tag.exception.TagEntityCannotBeUpdatedException;
import org.jobboard.adapter.out.persistence.jpa.tag.mapper.TagEntityGenericMapper;
import org.jobboard.application.port.out.persistence.tag.TagRepository;
import org.jobboard.domain.tag.Alias;
import org.jobboard.domain.tag.Tag;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TagAdapter implements TagRepository {


	private final JpaTagRepository tagRepository;

	private final TagEntityGenericMapper tagMapper;

	public TagAdapter(JpaTagRepository tagRepository, TagEntityGenericMapper tagMapper) {
		this.tagRepository = tagRepository;
		this.tagMapper = tagMapper;
	}


	@Override
	public Optional<Tag> save(Tag tag) {
		if (tagRepository.existsByName(tag.getName())) {
			return Optional.empty();
		}
		return Optional.of(tagMapper.toDomain(tagRepository
			.save(tagMapper.toDbo(tag))));
	}

	@Transactional
	@Override
	public Optional<Tag> update(Tag tag) {
		Optional<TagEntity> tagEntity = Optional.of(tagRepository.findByName(tag.getName()));
		tagEntity.ifPresentOrElse(
			tg -> {
				tg.setAliasName(tag.getAlias().name());
			},
			() -> {
				throw new TagEntityCannotBeUpdatedException(
					"This tag: \n"
						+ tag.toString()
						+ "\nIs not valid"
				);
			}
		);
		return Optional.of(tagMapper
			.toDomain(tagRepository.save(tagEntity.get())));
	}

	@Transactional
	@Override
	public long deleteByName(String name) {
		return tagRepository.deleteByName(name);
	}

	@Override
	public Optional<Tag> findByName(String name) {
		return Optional.of(tagMapper.toDomain(tagRepository.findByName(name)));
	}

	@Override
	public List<Tag> findAll() {
		List<Tag> tags = new ArrayList<>();
		tagRepository.findAll().forEach(tagEntity -> tags.add(tagMapper.toDomain(tagEntity)));
		return tags;
	}

	@Override
	public boolean existsByNameAndAliasId(String name, Alias alias) {
		return tagRepository.existsByNameAndAliasName(name, alias.name());
	}

	@Transactional
	@Override
	public List<Tag> saveAll(List<Tag> tags) {
		List<Tag> tagList = new ArrayList<>();
		tags
			.forEach(tag -> {
				save(tag).ifPresentOrElse(tagList::add, () -> {
					throw new TagEntityCannotBeSavedException(tag);
				});
			});
		return tagList;
	}

	@Override
	public boolean existsByName(String name) {
		return tagRepository.existsByName(name);
	}
}
