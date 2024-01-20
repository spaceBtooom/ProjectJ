package org.jobboard.adapter.out.persistence.jpa.vacancy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
