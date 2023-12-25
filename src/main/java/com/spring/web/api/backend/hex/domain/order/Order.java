package com.spring.web.api.backend.hex.domain.order;


import com.spring.web.api.backend.hex.domain.tag.Tag;

import java.time.ZonedDateTime;
import java.util.*;

public class Order {

	private UUID id;
	List<Tag> tags;
	Map<String, String> files;
	private String title;
	private String comment;
	private Integer price;
	private String urlSource;
	private ZonedDateTime createAt;
	private ZonedDateTime updateAt;
	private ZonedDateTime expireAt;

	public Order(final List<Tag> tags,
			 String title,
			 String comment,
			 Integer price,
			 String urlSource,
			 ZonedDateTime expireAt) {
		this.id = UUID.randomUUID();
		if(tags == null){
			this.tags=new ArrayList<>();
		}
		else{
			this.tags = tags;
		}
		this.files = new HashMap<>();
		this.title = title;
		this.comment = comment;
		this.price = price;
		this.urlSource = urlSource;
		this.expireAt = expireAt;
		this.createAt = ZonedDateTime.now();
	}

	public Map<String, String> getFiles() {
		return Collections.unmodifiableMap(files);
	}

	public List<Tag> getTags(){
		return Collections.unmodifiableList(this.tags);
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

	public String getUrlSource() {
		return urlSource;
	}

	public ZonedDateTime getCreateAt() {
		return createAt;
	}

	public ZonedDateTime getUpdateAt() {
		return updateAt;
	}

	public ZonedDateTime getExpireAt() {
		return expireAt;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Order order = (Order) o;
		return Objects.equals(id, order.id) && Objects.equals(tags, order.tags) && Objects.equals(files, order.files) && Objects.equals(title, order.title) && Objects.equals(comment, order.comment) && Objects.equals(price, order.price) && Objects.equals(urlSource, order.urlSource) && Objects.equals(createAt, order.createAt) && Objects.equals(expireAt, order.expireAt);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, tags, files, title, comment, price, urlSource, createAt, expireAt);
	}

	public void addTag(final Tag tag){
		this.tags.add(tag);
	}

	public void removeByTagId(UUID tagId) {
		this.tags.removeIf(tag->tag.getId().equals(tagId));
	}
}
