package com.spring.web.api.backend.hex.domain.orderFile.api.spring.webmvc.mapper.GenericMapper;

import com.spring.web.api.backend.GenericMapperRDR;
import com.spring.web.api.backend.hex.domain.orderFile.OrderFile;
import com.spring.web.api.backend.hex.domain.orderFile.api.spring.webmvc.dto.OrderFileRequest;
import com.spring.web.api.backend.hex.domain.orderFile.api.spring.webmvc.dto.OrderFileResponse;
import org.springframework.stereotype.Service;

@Service
public class OrderFileGenericMapper implements GenericMapperRDR<OrderFileRequest, OrderFile, OrderFileResponse> {
	@Override
	public OrderFile toDomain(OrderFileRequest orderFileRequest) {
		return new OrderFile("",
			orderFileRequest.filename(),
			orderFileRequest.orderId());
	}

	@Override
	public OrderFileResponse toResponse(OrderFile orderFile) {
		return new OrderFileResponse(orderFile.getFilename(),
			"/api/order/file/" + orderFile.getOrderId() + "/" + orderFile.getFilecode());
	}
}
