package com.spring.web.api.backend.vacancy;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.ZonedDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Data
@Entity
@Table(name="vacancies")
public class Vacancy {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VacancySeq")
	@SequenceGenerator(name="VacancySeq", sequenceName = "vacancies_id_seq",allocationSize = 1)
	private Long id;
	private String title;
	private String comment;
	private Integer price;
	@Column(name="create_at")
	// Where should Hibernate retrieve the date from? From the database, or from the current JVM?
	@CreationTimestamp(source = SourceType.DB)
	private ZonedDateTime createAt;
	@UpdateTimestamp(source = SourceType.DB)
	@Column(name="update_at")
	private ZonedDateTime updateAt;
}
