CREATE TABLE IF NOT EXISTS tags(
	id uuid PRIMARY KEY NOT NULL,
	name VARCHAR(60) NOT NULL DEFAULT 'Empty tag name',
	alias_id INTEGER

);

CREATE TABLE IF NOT EXISTS orders(
	id uuid PRIMARY KEY NOT NULL,
	title VARCHAR (50) NOT NULL,
	comment VARCHAR (256) NOT NULL,
	price INT,
	url_source VARCHAR(50),
	create_at TIMESTAMP(9) WITH TIME ZONE NOT NULL,
	update_at TIMESTAMP(9) WITH TIME ZONE,
	expire_at TIMESTAMP(9) WITH TIME ZONE
);

CREATE TABLE IF NOT EXISTS order_tags(
	tag_id uuid NOT NULL,
	order_id uuid NOT NULL,
	CONSTRAINT order_tag_fk_1 FOREIGN KEY(tag_id) REFERENCES tags(id) ON DELETE RESTRICT,
	CONSTRAINT order_tag_fk_2 FOREIGN KEY(order_id) REFERENCES orders(id) ON DELETE CASCADE
);
INSERT INTO tags (id, name, alias_id)
	VALUES('28f31d7f-de16-4f1d-9b61-f685fedc3a94','Java',1);

INSERT INTO tags (id, name, alias_id)
	VALUES('28f31d7f-de16-4f1d-9b62-f685fedc3a94','Python',1);
