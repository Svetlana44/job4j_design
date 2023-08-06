create table points1(
id serial primary key,
name varchar(15)
);

begin transaction isolation level serializable;

savepoint point1;

insert into points1(name) values('point1');

savepoint point2;

select * from points1;

release point1;

insert into points1(name) values('point2');

savepoint point2;

select * from points1;

delete from points1;

select * from points1;

rollback to point2;

select * from points1;

rollback;


