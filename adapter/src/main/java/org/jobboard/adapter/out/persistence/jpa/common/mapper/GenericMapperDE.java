package org.jobboard.adapter.out.persistence.jpa.common.mapper;

public interface GenericMapperDE <Domain, Entity>{
	Entity toDbo(Domain domain);
	Domain toDomain(Entity entity);
}
