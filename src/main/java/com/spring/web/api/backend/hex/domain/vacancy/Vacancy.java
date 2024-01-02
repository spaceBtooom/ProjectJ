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
import java.util.Objects;
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

	public ZonedDateTime getCreateAt() {
		return createAt;
	}

	public ZonedDateTime getUpdateAt() {
		return updateAt;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Vacancy vacancy = (Vacancy) o;
		return Objects.equals(title, vacancy.title) && Objects.equals(comment, vacancy.comment) && Objects.equals(createAt, vacancy.createAt);
	}

	@Override
	public int hashCode() {
		return Objects.hash(title, comment, createAt);
	}
}
