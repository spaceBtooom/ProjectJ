package com.spring.web.api.backend.hex.domain.db.springdata.dbo;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Setter
@Getter
public class OrderEntity {
	@Id
	private UUID id;


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
	List<TagEntity> tags;
}
