package org.jobboard.domain.order;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.jobboard.domain.attachedFile.AttachedOrderFile;
import org.jobboard.domain.tag.Tag;

import java.time.OffsetDateTime;
import java.util.*;

@Getter
@Setter
@RequiredArgsConstructor
public class Order {
	private final UUID id;
	private Map<String, Tag> tags;
	private Map<String, AttachedOrderFile> files;
	private OffsetDateTime createAt;

	private String title;
	private String comment;
	// need to add object
	private Integer price;
	private String urlSource;
	private OffsetDateTime updateAt;
	private OffsetDateTime expireAt;

	{
		tags = new HashMap<>();
		files = new HashMap<>();
		createAt = OffsetDateTime.now();
	}

	public Map<String, Tag> getTags() {
		return Collections.unmodifiableMap(tags);
	}
	public Map<String, AttachedOrderFile> getFiles() {
		return Collections.unmodifiableMap(files);
	}



	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Order order = (Order) o;
		return Objects.equals(id, order.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public void addTag(final String name) {

		this.tags.put(name, null);
	}

	public void addTagsToEmpty(final List<Tag> tagsList) {
		this.tags.clear();
		addTags(tagsList);
	}

	public void addTags(final List<Tag> tagsList) {
		tagsList
			.forEach(tag->
				tags.put(tag.getName(), tag));
	}

	public void addOrderFile(final String filecode) {

		this.files.put(filecode, null);
	}

	public void addOrderFilesToEmpty(final List<AttachedOrderFile> fileList) {
		this.files.clear();
		fileList.stream()
			.forEach(file->
				files.put(file.getFilecode(), file));
	}


	public void removeByTagName(String name) {
		this.tags.remove(name);
	}

	public void removeByFilecode(String filecode) {
		this.files.remove(filecode);
	}

	public void updateOrder() {
		this.updateAt = OffsetDateTime.now();
	}

}
