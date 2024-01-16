package com.spring.web.api.backend;

public interface GenericMapperDE <Domain, Entity>{
	Entity toDbo(Domain domain);
	Domain toDomain(Entity entity);
}
