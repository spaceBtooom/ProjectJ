package org.jobboard.adapter.out.persistence.jpa.attachedFile.mapper;

import org.jobboard.adapter.out.persistence.jpa.common.mapper.GenericMapperDE;
import org.jobboard.adapter.out.persistence.jpa.attachedFile.AttachedOrderFileEntity;
import org.jobboard.domain.attachedFile.AttachedOrderFile;
import org.springframework.stereotype.Service;

@Service
public class AttachedOrderFileEntityGenericMapper implements GenericMapperDE<AttachedOrderFile, AttachedOrderFileEntity> {
	@Override
	public AttachedOrderFileEntity toDbo(AttachedOrderFile aoFile) {
		if(aoFile == null){
			return null;
		}
		return new AttachedOrderFileEntity(aoFile.getId(),
			aoFile.getFilecode(),
			aoFile.getFilename(),
			aoFile.getOrderId());
	}

	@Override
	public AttachedOrderFile toDomain(AttachedOrderFileEntity aoeFile) {
		if(aoeFile == null){
			return null;
		}
		AttachedOrderFile file = new AttachedOrderFile(aoeFile.getId());
		file.setFilecode(aoeFile.getFilecode());
		file.setFilename(aoeFile.getFilename());
		file.setOrderId(aoeFile.getOrderId());
		return file;
	}
}
