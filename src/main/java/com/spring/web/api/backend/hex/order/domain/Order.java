package com.spring.web.api.backend.hex.order.domain;


import com.spring.web.api.backend.hex.orderFile.domain.OrderFile;
import com.spring.web.api.backend.hex.tag.domain.Tag;

import java.time.OffsetDateTime;
import java.util.*;

public class Order {

	private UUID id;
	private List<Tag> tags;
	private List<OrderFile> files;
	private String title;
	private String comment;
	private Integer price;
	private String urlSource;
	private OffsetDateTime createAt;
	private OffsetDateTime updateAt;
	private OffsetDateTime expireAt;

	public Order(final List<Tag> tags,
			 String title,
			 String comment,
			 Integer price,
			 String urlSource,
			 OffsetDateTime expireAt) {
		this.id = UUID.randomUUID();
		if(tags == null){
			this.tags=new ArrayList<>();
		}
		else{
			this.tags = tags;
		}
		if(files == null){
			this.files = new ArrayList<>();
		}
		else{
			this.files = files;
		}
		this.files = new ArrayList<>();
		this.title = title;
		this.comment = comment;
		this.price = price;
		this.urlSource = urlSource;

		this.createAt = OffsetDateTime.now();
		if (expireAt.isBefore(this.createAt)) {
			throw new OrderTimeException("Expired time is not valid. Must be after current time");
		}
		this.expireAt = expireAt;

	}

	public Order(UUID id,
			 List<Tag> tags,
			 List<OrderFile> files,
			 String title, String comment,
			 Integer price, String urlSource,
			 OffsetDateTime createAt, OffsetDateTime updateAt, OffsetDateTime expireAt){
		this.id = id;

		if(tags == null){
			this.tags=new ArrayList<>();
		}
		else{
			this.tags = tags;
		}
		if(files == null){
			this.files = new ArrayList<>();
		}
		else{
			this.files = files;
		}



		this.title = title;
		this.comment = comment;
		this.price = price;
		this.urlSource = urlSource;
		this.createAt = createAt;
		this.updateAt = updateAt;
		this.expireAt = expireAt;

	}

	public List<OrderFile> getFiles() {
		return this.files;
	}

	public List<Tag> getTags(){
		return this.tags;
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

	public OffsetDateTime getCreateAt() {
		return createAt;
	}

	public OffsetDateTime getUpdateAt() {
		return updateAt;
	}

	public OffsetDateTime getExpireAt() {
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

	public void addTags(final List<Tag> tag){
		this.tags= new ArrayList<>(this.tags);
		this.tags.addAll(tag);
	}
	public void removeByTagId(UUID tagId) {
		this.tags.removeIf(tag->tag.getId().equals(tagId));
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public void updateOrder(){
		this.updateAt=OffsetDateTime.now();
	}
}
