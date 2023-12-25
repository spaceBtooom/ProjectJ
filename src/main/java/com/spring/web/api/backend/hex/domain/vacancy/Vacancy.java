package com.spring.web.api.backend.hex.domain.vacancy;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.ZonedDateTime;
import java.util.UUID;


public class Vacancy {
	private UUID id;
	private String title;
	private String comment;
	private Integer price;
	private ZonedDateTime createAt;
	private ZonedDateTime updateAt;

	public Vacancy(UUID id, String title, String comment, Integer price) {
		this.id = id;
		this.title = title;
		this.comment = comment;
		this.price = price;
		this.createAt = ZonedDateTime.now();
	}
}
