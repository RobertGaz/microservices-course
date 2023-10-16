create table if not exists song_metadata (
	id serial primary key, 
	title varchar(50),
	artist varchar(50),
	album varchar(50),
	duration varchar(50),
	resource_id varchar(50)
);
