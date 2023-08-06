create table users_name (
    id serial primary key,
    name varchar(50)
);

insert into users_name(name) values('Jim');
insert into users_name(name) values('Mary');
insert into users_name(name) values('Pol');

/*------ uncommitted ------*/
/* for every session */
set session transaction isolation level read uncommitted;
/* two times, for every session */
start transaction;
select * from users_name;
/* first transaction */
insert into users_name(name) values('Nicke');
delete from users_name where id = 3;
update users_name set name = 'Mary Jey' where id = 2;

/* second transaction */
select * from users_name;
select name from users_name where id = 2;

/* first transaction */
rollback;
/* second transaction */
select * from users_name;
select name from users_name where id = 2;

/*------ read committed ------*/

begin transaction isolation level read committed;
/* first transaction */
insert into users_name(name) values('Jim');
insert into users_name(name) values('Mary');
insert into users_name(name) values('Pol');

insert into users_name(name) values('Nicke');
delete from users_name where id = 3;
update users_name set name = 'Mary Jey' where id = 2;

/* for every session */
select * from users_name;
/* first transaction */
commit;
/* second transaction */
select * from users_name;
commit;

/*------ repeatable read ------*/

/* for every session */
begin transaction isolation level repeatable read;

select * from users_name;

/* first transaction */
insert into users_name(name) values('Nicke');
delete from users_name where id = 3;
update users_name set name = 'Mary Jey2' where id = 2;

/* second transaction */
update users_name set name = 'Mary Jey3' where id = 2;

/* first transaction */
rollback;

/* second transaction */
rollback;
/* for every session */
begin transaction isolation level repeatable read;
select * from users_name;

/* first transaction */
insert into users_name(name) values('Nicke');
delete from users_name where id = 3;
update users_name set name = 'Mary Jey' where id = 2;

/* second transaction */
update users_name set name = 'Mary Jey' where id = 2;

/* first transaction */
commit;
/* second transaction */
rollback;

/*------ serializable ------*/

/* for every session */
begin transaction isolation level serializable;
/* first transaction */
select * from users_name where id = 2;
update users_name set name = 'Mary Jey4' where id = 2;

/* second transaction */
select * from users_name where id = 2;
update users_name set name = 'Mary Jey5' where id = 2;

/* for every session */
commit;