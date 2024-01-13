package com.spring.web.api.backend.hex.orderFile.spi.springdata.dbo;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "order_files")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Setter
@Getter
public class OrderFileEntity {
	@Id
	private UUID id;
	private String filecode;
	private String filename;

	@Column(name="order_id")
	private UUID orderId;
}
