CREATE TABLE IF NOT EXISTS tags(
	id uuid PRIMARY KEY NOT NULL DEFAULT gen_random_uuid(),
	name VARCHAR(60) NOT NULL DEFAULT 'Empty tag name',
	alias_id INTEGER

);

CREATE TABLE IF NOT EXISTS orders(
	id uuid PRIMARY KEY NOT NULL DEFAULT gen_random_uuid(),
	title VARCHAR (50) NOT NULL,
	comment VARCHAR (256) NOT NULL,
	price INT,
	url_source VARCHAR(50),
	create_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
	update_at TIMESTAMP WITH TIME ZONE,
	expire_at TIMESTAMP WITH TIME ZONE
);

CREATE TABLE IF NOT EXISTS order_tags(
	tag_id uuid NOT NULL,
	order_id uuid NOT NULL,
	CONSTRAINT order_tag_fk_1 FOREIGN KEY(tag_id) REFERENCES tags(id) ON DELETE RESTRICT,
	CONSTRAINT order_tag_fk_2 FOREIGN KEY(order_id) REFERENCES orders(id) ON DELETE CASCADE
);
INSERT INTO tags (name, alias_id)
	VALUES('Java',1);

INSERT INTO tags (name, alias_id)
	VALUES('Python',1);
