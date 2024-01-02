package com.spring.web.api.backend.hex.domain.vacancy.spi.springdata.dbo;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name="vacancies")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Setter
@Getter
public class VacancyEntity {
	@Id
	private UUID id;
	private String title;
	private String comment;
	private Integer price;

	@Column(name="create_at")
	@CreationTimestamp(source = SourceType.DB)
	private ZonedDateTime createAt;
	@UpdateTimestamp(source = SourceType.DB)
	@Column(name="update_at")
	private ZonedDateTime updateAt;
}
