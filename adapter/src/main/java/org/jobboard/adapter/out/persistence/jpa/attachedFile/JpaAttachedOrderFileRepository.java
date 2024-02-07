package org.jobboard.adapter.out.persistence.jpa.attachedFile;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JpaAttachedOrderFileRepository extends CrudRepository<AttachedOrderFileEntity, UUID> {
	Integer countByOrderId(UUID id);

	List<AttachedOrderFileEntity> findByOrderId(UUID id);

	boolean existsByFilecode(String filecode);

	boolean existsByOrderId(UUID id);

	Optional<AttachedOrderFileEntity> findByFilecode(String filecode);

	long deleteByOrderIdAndFilecode(UUID id, String filecode);

	long deleteByOrderId(UUID id);
}
