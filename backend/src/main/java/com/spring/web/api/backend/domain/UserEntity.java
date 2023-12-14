package com.spring.web.api.backend.domain;

import jakarta.persistence.*;
import lombok.*;


@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
@Entity
@Table(name="users")
public class UserEntity {

	@Id
	String username;

	String name;
}
