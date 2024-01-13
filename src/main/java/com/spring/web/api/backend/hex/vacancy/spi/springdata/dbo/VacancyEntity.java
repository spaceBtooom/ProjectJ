package com.spring.web.api.backend.hex.vacancy.spi.springdata.dbo;

import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;
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

	@Column(name="create_at",columnDefinition = "TIMESTAMP(9) WITH TIME ZONE")
	private OffsetDateTime createAt;
	@Column(name="update_at",columnDefinition = "TIMESTAMP(9) WITH TIME ZONE")
	private OffsetDateTime updateAt;
}