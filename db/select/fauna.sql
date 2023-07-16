create table fauna(
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna (name,avg_age,discovery_date) values ('tiger',8030,to_date('05 Dec 1758', 'DD Mon YYYY'));
insert into fauna (name,avg_age,discovery_date) values ('puma',3650,to_date('05 Dec 1553', 'DD Mon YYYY'));
insert into fauna (name,avg_age,discovery_date) values ('fishka',100,to_date('05 Dec 0000', 'DD Mon YYYY'));
insert into fauna (name,avg_age,discovery_date) values ('cherepaha',30000,to_date('05 Dec 0000', 'DD Mon YYYY'));
insert into fauna (name,avg_age,discovery_date) values ('small_cherepaha',13000,null);
insert into fauna (name,avg_age,discovery_date) values ('very_small_cherepaha',3000,to_date('05 Dec 1950', 'DD Mon YYYY'));

select * from fauna where name like '%fish%';
select * from fauna where avg_age between 10000 and 21000;
select * from fauna where discovery_date IS NULL;
select * from fauna where discovery_date < to_date('01 Jan 1950', 'DD Mon YYYY');