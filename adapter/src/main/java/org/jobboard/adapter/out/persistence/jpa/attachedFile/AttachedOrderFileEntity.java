package org.jobboard.adapter.out.persistence.jpa.attachedFile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "order_files")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Setter
@Getter
public class AttachedOrderFileEntity {
	@Id
	private UUID id;

	private String filecode;
	private String filename;

	@Column(name="order_id")
	private UUID orderId;
}
