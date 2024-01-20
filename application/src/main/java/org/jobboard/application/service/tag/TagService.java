package org.jobboard.application.service.tag;


import org.jobboard.application.port.in.tag.TagUseCase;
import org.jobboard.application.port.out.persistence.tag.TagRepository;
import org.jobboard.application.service.tag.exception.TagException;
import org.jobboard.domain.tag.Alias;
import org.jobboard.domain.tag.Tag;

import java.util.List;
import java.util.Optional;

public class TagService implements TagUseCase {

	private final TagRepository tagRepository;

	public TagService(TagRepository tagRepository) {
		this.tagRepository = tagRepository;
	}

	@Override
	public Optional<Tag> save(Tag tag) {
		return tagRepository.save(tag);
	}

	@Override
	public Optional<Tag> update(Tag tag) throws TagException {
		return tagRepository.update(tag);
	}

	@Override
	public long deleteByName(String name) {
		return tagRepository.deleteByName(name);
	}

	@Override
	public Optional<Tag> findByName(String name) {
		return tagRepository.findByName(name);
	}

	@Override
	public List<Tag> findAll() {
		return tagRepository.findAll();
	}

	@Override
	public boolean existsByName(String name) {
		return tagRepository.existsByName(name);
	}

	@Override
	public boolean existsByNameAndAliasId(String name, Alias alias) {
		return tagRepository.existsByNameAndAliasId(name, alias);
	}

	@Override
	public List<Tag> saveAll(List<Tag> list) {
		return tagRepository.saveAll(list);
	}
}
