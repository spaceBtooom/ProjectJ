package org.jobboard.adapter.out.persistence.jpa.tag;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
