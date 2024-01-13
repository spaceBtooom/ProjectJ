package com.spring.web.api.backend;

import com.spring.web.api.backend.hex.order.domain.OrderTimeException;

public interface GenericMapperDE <Domain, Entity>{
	Entity toDbo(Domain domain);
	Domain toDomain(Entity entity) throws OrderTimeException;
}
