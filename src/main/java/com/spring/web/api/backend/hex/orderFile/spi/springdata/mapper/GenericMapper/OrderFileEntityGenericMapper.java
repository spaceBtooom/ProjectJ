package com.spring.web.api.backend.hex.orderFile.spi.springdata.mapper.GenericMapper;

import com.spring.web.api.backend.GenericMapperDE;
import com.spring.web.api.backend.hex.orderFile.domain.OrderFile;
import com.spring.web.api.backend.hex.orderFile.spi.springdata.dbo.OrderFileEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderFileEntityGenericMapper implements GenericMapperDE<OrderFile, OrderFileEntity> {
	@Override
	public OrderFileEntity toDbo(OrderFile orderFile) {
		if(orderFile == null){
			return null;
		}
		return new OrderFileEntity(orderFile.getId(),
			orderFile.getFilecode(),
			orderFile.getFilename(),
			orderFile.getOrderId());
	}

	@Override
	public OrderFile toDomain(OrderFileEntity orderFileEntity) {
		if(orderFileEntity == null){
			return null;
		}
		return new OrderFile(orderFileEntity.getId(),
			orderFileEntity.getFilecode(),
			orderFileEntity.getFilename(),
			orderFileEntity.getOrderId());
	}
}
