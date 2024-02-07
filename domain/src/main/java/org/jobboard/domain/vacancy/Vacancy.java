package org.jobboard.domain.vacancy;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Objects;
import java.util.UUID;
@Getter
@Setter
@RequiredArgsConstructor
public class Vacancy {
	@Setter(AccessLevel.NONE)
	private final UUID id;

	private String title;
	private String comment;
	private Integer price;

	private OffsetDateTime createAt;
	private OffsetDateTime updateAt;

	{
		createAt=OffsetDateTime.now();
	}


	public Vacancy updateVacancy(Vacancy vacancy){
		this.title = vacancy.title == null ? this.title : vacancy.title;
		this.comment = vacancy.comment == null ? this.comment : vacancy.comment;
		this.price = vacancy.price == null ? this.price : vacancy.price;
		this.updateAt = OffsetDateTime.now(ZoneId.of("+00:00"));
		return this;
	}

}
