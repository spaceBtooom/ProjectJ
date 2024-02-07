package org.jobboard.application.port.out.persistence.attachedOderFile;

import org.jobboard.domain.attachedFile.AttachedOrderFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AttachedOrderFileRepository {
	public Optional<AttachedOrderFile> save(AttachedOrderFile file);

	public List<AttachedOrderFile> findByOrderId(UUID id);

	Integer countByOrderId(UUID id);

	boolean existsByFilecode(String filecode);

	boolean existsByOrderId(UUID id);
	String findFileNameByFilecode(String filecode);

	long deleteByOrderIdAndFilecode(UUID id, String filecode);

	long deleteByOrderId(UUID id);


}
