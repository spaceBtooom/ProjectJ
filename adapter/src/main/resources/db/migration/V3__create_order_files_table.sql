CREATE TABLE IF NOT EXISTS order_files(
	id uuid PRIMARY KEY NOT NULL,
	filecode VARCHAR(16) NOT NULL,
	filename VARCHAR(40) NOT NULL,
	order_id uuid NOT NULL,
	FOREIGN KEY(order_id) REFERENCES orders(id) ON DELETE CASCADE
);
