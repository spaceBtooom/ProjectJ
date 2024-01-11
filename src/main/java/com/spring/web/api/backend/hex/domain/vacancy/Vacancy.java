package com.spring.web.api.backend.hex.domain.vacancy;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;


public class Vacancy {
	private UUID id;
	private String title;
	private String comment;
	private Integer price;
	private OffsetDateTime createAt;
	private OffsetDateTime updateAt;

	public Vacancy(String title, String comment, Integer price) {
		this.id = UUID.randomUUID();
		this.title = title;
		this.comment = comment;
		this.price = price;
		this.createAt = OffsetDateTime.now();
	}

	public Vacancy(UUID id, String title, String comment, Integer price, OffsetDateTime createAt, OffsetDateTime updateAt) {
		this.id = id;
		this.title = title;
		this.comment = comment;
		this.price = price;
		this.createAt = createAt;
		this.updateAt = updateAt;
	}

	public UUID getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getComment() {
		return comment;
	}

	public Integer getPrice() {
		return price;
	}

	public OffsetDateTime getCreateAt() {
		return createAt;
	}

	public OffsetDateTime getUpdateAt() {
		return updateAt;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Vacancy vacancy = (Vacancy) o;
		return Objects
			.equals(id, vacancy.id)
			&& Objects.equals(title, vacancy.title)
			&& Objects.equals(comment, vacancy.comment)
			&& Objects.equals(price, vacancy.price)
			&& createAt.isEqual(vacancy.createAt)
			&& updateAt.isEqual(vacancy.updateAt);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title, comment, createAt);
	}
}
