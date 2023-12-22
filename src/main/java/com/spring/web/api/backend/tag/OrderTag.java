package com.spring.web.api.backend.tag;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name="tags")
public class OrderTag {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TagSeq")
	@SequenceGenerator(name="TagSeq", sequenceName = "tags_id_seq", allocationSize = 1)
	private Long id;
	private String name;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "alias")
	private Alias alias;

}
