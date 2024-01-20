package org.jobboard.adapter.in.rest.attachedFile.mapper;

import org.jobboard.adapter.in.rest.attachedFile.dto.AttachedOrderFileRequest;
import org.jobboard.adapter.in.rest.attachedFile.dto.AttachedOrderFileResponse;
import org.jobboard.adapter.in.rest.common.mappers.GenericMapperRDR;
import org.jobboard.domain.attachedFile.AttachedOrderFile;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderFileGenericMapper implements GenericMapperRDR<AttachedOrderFileRequest, AttachedOrderFile, AttachedOrderFileResponse> {
	@Override
	public AttachedOrderFile toDomain(AttachedOrderFileRequest aorf) {
		if(aorf == null){
			return null;
		}
		AttachedOrderFile aof = new AttachedOrderFile(UUID.randomUUID());
		aof.setFilename(aorf.filename());
		aof.setOrderId(aorf.orderId());
		return aof;
	}

	@Override
	public AttachedOrderFileResponse toResponse(AttachedOrderFile aof) {
		if(aof == null){
			return null;
		}
		return new AttachedOrderFileResponse(aof.getFilename(),
			"/api/order/file/" + aof.getOrderId() + "/" + aof.getFilecode());
	}
}
