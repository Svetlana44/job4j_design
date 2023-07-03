create table animals2(
id serial primary key,
	name varchar(255),
	area text,
	eat text
);
insert into animals2 (name,area,eat) values ('Гризли','США','мясо');
update animals2 set name='Гриззли';
delete from animals2;