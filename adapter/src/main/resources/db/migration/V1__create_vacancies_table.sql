CREATE TABLE IF NOT EXISTS vacancies(
	id uuid PRIMARY KEY NOT NULL,
	title VARCHAR (50) NOT NULL,
	comment VARCHAR (256) NOT NULL,
	price INT,
	create_at TIMESTAMP(9) WITH TIME ZONE NOT NULL,
	update_at TIMESTAMP(9) WITH TIME ZONE
);
INSERT INTO vacancies (id,title, comment, price, create_at)
	VALUES('11f31d7f-de16-4f1d-9b61-f685fedc3a94','hello','world',1,CURRENT_TIMESTAMP);

INSERT INTO vacancies (id,title, comment, create_at)
	VALUES('28a31d8f-de16-4f1d-9b61-f685fedc3a94','Python','Need a fella',CURRENT_TIMESTAMP);
