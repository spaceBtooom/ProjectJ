package org.jobboard.adapter.out.persistence.jpa.attachedFile;

import jakarta.transaction.Transactional;
import org.jobboard.adapter.out.persistence.jpa.attachedFile.mapper.AttachedOrderFileEntityGenericMapper;
import org.jobboard.application.port.out.persistence.attachedOderFile.AttachedOrderFileRepository;
import org.jobboard.domain.attachedFile.AttachedOrderFile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AttachedAttachedOrderFileAdapter implements AttachedOrderFileRepository {
	private final JpaAttachedOrderFileRepository repository;
	private final AttachedOrderFileEntityGenericMapper orderFileMapper;

	public AttachedAttachedOrderFileAdapter(JpaAttachedOrderFileRepository repository, AttachedOrderFileEntityGenericMapper orderFileMapper) {
		this.repository = repository;
		this.orderFileMapper = orderFileMapper;
	}

	@Override
	public Optional<AttachedOrderFile> save(AttachedOrderFile file) {
		return Optional
			.of(orderFileMapper
				.toDomain(
					repository.save(orderFileMapper
						.toDbo(file))
		));
	}

	@Override
	public List<AttachedOrderFile> findByOrderId(UUID id) {
		return repository
			.findByOrderId(id)
			.stream()
			.map(orderFileMapper::toDomain)
			.toList();
	}

	@Override
	public Integer countByOrderId(UUID id) {
		return repository.countByOrderId(id);
	}

	@Override
	public boolean existsByOrderId(UUID id) {
		return repository.existsByOrderId(id);
	}

	@Override
	public boolean existsByFilecode(String filecode) {
		return !repository.existsByFilecode(filecode);
	}

	// DB have to send name:string but not object(AttachedOrderFile)
	@Override
	public String findFileNameByFilecode(String filecode) {

		Optional<AttachedOrderFileEntity> byfilecode = repository
			.findByFilecode(filecode);

		return byfilecode.map(AttachedOrderFileEntity::getFilename).
			orElseThrow(()->new RuntimeException(this.getClass().getName()));
	}

	// Before each delete jpa take entities with select, and only after that it delete
	// Work around annotation @Query and @Modifying
	// Problem: persist context have to be updated since DML operations
	@Transactional
	@Override
	public long deleteByOrderIdAndFilecode(UUID id, String filecode) {
		return repository.deleteByOrderIdAndFilecode(id, filecode);
	}

	// Before each delete jpa take entities with select, and only after that it delete
	// Work around annotation @Query and @Modifying
	// Problem: persist context have to be updated since DML operations
	@Transactional
	@Override
	public long deleteByOrderId(UUID id) {
		return repository.deleteByOrderId(id);
	}
}
