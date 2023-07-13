create table position(
    id serial primary key,
    name varchar(255));

create table employees(
    id serial primary key,
    name varchar(255),
    position_id int references position(id)
);

insert into position(name) values ('QA');
insert into employees(name, position_id) VALUES ('Sveta', 1);

select * from employees;

select * from position where id in (select position_id from employees);