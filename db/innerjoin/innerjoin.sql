create table users(
id serial primary key,
name varchar(20),
nikename varchar(15)
);

create table roles(
id serial primary key,
name varchar(20),
users_id int references user (id)
);

select u.id,u.name as "User name", nikename, r.id, r.name RoleName from users u inner join roles r on u.id=r.users_id;

select u.id,u.name as "User name", nikename, r.id, r.name
 from users u inner join roles r on u.id=r.users_id
 where "User name" like 'Jon';

select u.id,u.name as "User name", nikename, r.id, r.name RoleName from users u inner join roles r on u.id=r.users_id
where RoleName like 'admin';

