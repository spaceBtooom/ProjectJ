package com.spring.web.api.backend.tag;

import org.springframework.data.repository.CrudRepository;

public interface AliasRepository extends CrudRepository<Alias, Long> {
	boolean existsByShortName(String shortName);
	Alias findByShortName(String shortName);
}
