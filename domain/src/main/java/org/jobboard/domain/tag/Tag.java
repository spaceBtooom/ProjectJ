package org.jobboard.domain.tag;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@RequiredArgsConstructor
@Getter
@Setter
public class Tag {
	private final UUID id;
	private String name;
	private Alias alias;

	@Override
	public String toString() {
		return "Tag{" +
			"name='" + name + '\'' +
			", alias='" + alias.toString() +
			'}';
	}
}
