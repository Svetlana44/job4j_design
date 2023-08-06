create table points1(
id serial primary key,
name varchar(15)
);

begin transaction;

savepoint point1;

insert into points1(name) values('point1');

savepoint point2;

select * from points1;

release point1;

