package org.jobboard.domain.attachedFile;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class AttachedOrderFile {
	private final UUID id;
	private String filecode;
	private String filename;
	private UUID orderId;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AttachedOrderFile attacheFile = (AttachedOrderFile) o;
		return Objects.equals(id, attacheFile.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
