package com.spring.web.api.backend.order;

import com.spring.web.api.backend.tag.OrderTag;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OrderSeq")
	@SequenceGenerator(name="OrderSeq", sequenceName = "orders_id_seq",allocationSize = 1)
	private Long id;

	private String title;

	private String comment;

	private Integer price;



	@Column(name="url_source")
	private String urlSource;

	@Column(name="create_at")
	@CreationTimestamp(source = SourceType.DB)
	private ZonedDateTime createAt;

	@UpdateTimestamp(source = SourceType.DB)
	@Column(name="update_at")
	private ZonedDateTime updateAt;

	@Column(name="expire_at")
	private ZonedDateTime expireAt;

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "order_tags",
		inverseJoinColumns = {@JoinColumn(name="tag_id")}
	)
	List<OrderTag> tags;
}
