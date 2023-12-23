CREATE TABLE IF NOT EXISTS order_attached_files(
	id serial PRIMARY KEY,
	filecode VARCHAR(16) NOT NULL,
	filename VARCHAR(40) NOT NULL,
	order_id INTEGER NOT NULL,
	FOREIGN KEY(order_id) REFERENCES orders(id) ON DELETE CASCADE
);
