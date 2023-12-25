CREATE TABLE IF NOT EXISTS vacancies(
	id uuid PRIMARY KEY NOT NULL DEFAULT gen_random_uuid(),,
	title VARCHAR (50) NOT NULL,
	comment VARCHAR (256) NOT NULL,
	price INT,
	create_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
	update_at TIMESTAMP WITH TIME ZONE
);
INSERT INTO vacancies (title, comment, price, create_at)
	VALUES('hello','world',1,CURRENT_TIMESTAMP);

INSERT INTO vacancies (title, comment, create_at)
	VALUES('Python','Need a fella',CURRENT_TIMESTAMP);
