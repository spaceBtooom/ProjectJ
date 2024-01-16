package com.spring.web.api.backend.hex.tag.spi.springdata.dbo;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name="tags")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Setter
@Getter
public class TagEntity {
	@Id
	private UUID id;
	private String name;
	@Column(name="alias_name")
	private String aliasName;
}
