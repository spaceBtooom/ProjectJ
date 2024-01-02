package com.spring.web.api.backend.hex.domain.order.spi.springdata.mapper;

import com.spring.web.api.backend.hex.domain.order.spi.springdata.dbo.OrderEntity;
import com.spring.web.api.backend.hex.domain.order.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderEntityMapper {
	Order toDomain(OrderEntity orderEntity);
	OrderEntity toDbo(Order order);
}
