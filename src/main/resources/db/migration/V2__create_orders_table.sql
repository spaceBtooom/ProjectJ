CREATE TABLE IF NOT EXISTS aliases(
	id serial PRIMARY KEY,
	short_name VARCHAR(10) NOT NULL DEFAULT 'NONE'
);

CREATE TABLE IF NOT EXISTS tags(
	id serial PRIMARY KEY,
	name VARCHAR(60) NOT NULL DEFAULT 'Empty tag name',
	alias INTEGER,
	CONSTRAINT alias_id_ref FOREIGN KEY(alias) REFERENCES aliases(id)
);

CREATE TABLE IF NOT EXISTS orders(
	id serial PRIMARY KEY,
	title VARCHAR (50) NOT NULL,
	comment VARCHAR (256) NOT NULL,
	price INT,
	url_source VARCHAR(50),
	create_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
	update_at TIMESTAMP WITH TIME ZONE,
	expire_at TIMESTAMP WITH TIME ZONE
);

CREATE TABLE IF NOT EXISTS order_tags(
	tag_id INTEGER NOT NULL,
	order_id INTEGER NOT NULL,
	CONSTRAINT order_tag_fk_1 FOREIGN KEY(tag_id) REFERENCES tags(id),
	CONSTRAINT order_tag_fk_2 FOREIGN KEY(order_id) REFERENCES orders(id)
);


INSERT INTO aliases (short_name)
	VALUES('PRIORITY');
INSERT INTO aliases (short_name)
	VALUES('DESC');
INSERT INTO aliases (short_name)
	VALUES('LANG');
INSERT INTO aliases (short_name)
	VALUES('STATUS');

INSERT INTO tags (name, alias)
	VALUES('Java', 1);

INSERT INTO tags (name, alias)
	VALUES('Premium', 4);

INSERT INTO vacancies (title, comment, price, create_at)
	VALUES('hello','world',1,CURRENT_TIMESTAMP);

INSERT INTO vacancies (title, comment, create_at)
	VALUES('Python','Need a fella',CURRENT_TIMESTAMP);
