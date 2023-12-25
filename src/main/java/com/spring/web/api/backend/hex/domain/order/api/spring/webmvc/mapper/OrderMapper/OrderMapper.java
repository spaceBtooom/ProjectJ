package com.spring.web.api.backend.hex.domain.order.api.spring.webmvc.mapper.OrderMapper;

import com.spring.web.api.backend.hex.domain.order.Order;
import com.spring.web.api.backend.hex.domain.order.api.spring.webmvc.dto.OrderRequest;
import com.spring.web.api.backend.hex.domain.order.api.spring.webmvc.dto.OrderResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
	OrderResponse toResponse(Order order);
	Order toOrder(OrderRequest orderRequest);

}
