package com.spring.web.api.backend.order.file;


import com.spring.web.api.backend.order.Order;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "order_attached_files")

public class OrderAttachedFile {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OrderAttacheFileSeq")
	@SequenceGenerator(name="OrderAttacheFileSeq", sequenceName = "order_attached_files_id_seq",allocationSize = 1)
	Long id;

	String filecode;

	String filename;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="order_id")
	Order order;
}
