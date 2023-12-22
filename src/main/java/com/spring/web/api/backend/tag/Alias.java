package com.spring.web.api.backend.tag;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name="aliases")
//Can be enum{priority,lang,status,desc}
//It's not unique (admin can create 1:DESC; 62:DESC)
public class Alias {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AliasSeq")
	@SequenceGenerator(name="AliasSeq", sequenceName = "aliases_id_seq",allocationSize = 1)
	Long id;

	@Column(name="short_name")
	private String shortName;
}
