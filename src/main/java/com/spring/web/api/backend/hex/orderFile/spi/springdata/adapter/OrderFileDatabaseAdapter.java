package com.spring.web.api.backend.hex.orderFile.spi.springdata.adapter;

import com.spring.web.api.backend.hex.orderFile.domain.OrderFile;
import com.spring.web.api.backend.hex.orderFile.spi.OrderFileSpi;
import com.spring.web.api.backend.hex.orderFile.spi.springdata.db.SpringDataOrderFileRepository;
import com.spring.web.api.backend.hex.orderFile.spi.springdata.mapper.GenericMapper.OrderFileEntityGenericMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderFileDatabaseAdapter implements OrderFileSpi {
	private final SpringDataOrderFileRepository orderFileRepository;
	private final OrderFileEntityGenericMapper orderFileMapper;

	public OrderFileDatabaseAdapter(SpringDataOrderFileRepository orderFileRepository, OrderFileEntityGenericMapper orderFileMapper) {
		this.orderFileRepository = orderFileRepository;
		this.orderFileMapper = orderFileMapper;
	}

	@Override
	public Optional<OrderFile> save(OrderFile file) {
		return Optional.of(orderFileMapper.toDomain(
						orderFileRepository.save(
							orderFileMapper.toDbo(file))
		));
	}

	@Override
	public List<OrderFile> findByOrderId(UUID id) {
		return orderFileRepository.findByOrderId(id).stream().map(orderFileMapper::toDomain).toList();
	}

	@Override
	public Integer countByOrderId(UUID id) {
		return orderFileRepository.countByOrderId(id);
	}

	@Override
	public boolean existsByOrderId(UUID id) {
		return orderFileRepository.existsByOrderId(id);
	}

	@Override
	public boolean existsByFilecode(String filecode) {
		return orderFileRepository.existsByFilecode(filecode);
	}

	@Override
	public String findFilenameByFilecode(String filecode) {
		return orderFileRepository.findByFilecode(filecode).get().getFilename();
	}

	// Before each delete jpa take entities with select, and only after that it delete
	// Work around annotation @Query and @Modifying
	// Problem: persist context have to be updated since DML operations
	@Transactional
	@Override
	public long deleteByOrderIdAndFilecode(UUID id, String filecode) {
		return orderFileRepository.deleteByOrderIdAndFilecode(id, filecode);
	}

	// Before each delete jpa take entities with select, and only after that it delete
	// Work around annotation @Query and @Modifying
	// Problem: persist context have to be updated since DML operations
	@Transactional
	@Override
	public long deleteByOrderId(UUID id) {
		return orderFileRepository.deleteByOrderId(id);
	}
}
