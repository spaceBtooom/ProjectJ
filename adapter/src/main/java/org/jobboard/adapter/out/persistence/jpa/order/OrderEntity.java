package org.jobboard.adapter.out.persistence.jpa.order;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;
import org.jobboard.adapter.out.persistence.jpa.attachedFile.AttachedOrderFileEntity;
import org.jobboard.adapter.out.persistence.jpa.tag.TagEntity;
import org.jobboard.domain.tag.Tag;

import java.time.OffsetDateTime;
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
	private OffsetDateTime createAt;

	@UpdateTimestamp(source = SourceType.DB)
	@Column(name="update_at")
	private OffsetDateTime updateAt;

	@Column(name="expire_at")
	private OffsetDateTime expireAt;

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "order_tags",
		joinColumns = {@JoinColumn(name = "order_id")},
		inverseJoinColumns = {@JoinColumn(name = "tag_id")}
	)
	private List<TagEntity> tags;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "orderId")
	private List<AttachedOrderFileEntity> files;
}
