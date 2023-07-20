create table departments (
id serial primary key,
name varchar(20)
);

create table employees (
id serial primary key,
name varchar(20),
id_departments int references departments(id)
);

create table teens (
id serial primary key,
name varchar(20),
gender varchar(10)
);

insert into departments (name) values('ООКПП');
insert into departments (name) values('ОТФС');
insert into departments (name) values('ОТАБС');
insert into departments (name) values('НОУНЭЙМ');

insert into employees (name,id_departments) values('Ник',1);
insert into employees (name,id_departments) values('Ник2',1);
insert into employees (name,id_departments) values('Ниф',2);
insert into employees (name,id_departments) values('Наф',3);
insert into employees (name,id_departments) values('Наф-Наф',2);

insert into teens(name,gender) values('Pol','gentleman');
insert into teens(name,gender) values('Polina','ledy');
insert into teens(name,gender) values('Dan','gentleman');
insert into teens(name,gender) values('Roy','gentleman');
insert into teens(name,gender) values('Diana','ledy');

select * from employees;

select * from departments d left join employees  e on d.id=e.id_departments;
select * from departments d right join employees  e on d.id=e.id_departments;
select * from departments d full join employees  e on d.id=e.id_departments;

select * from departments d left join employees  e on d.id=e.id_departments
where e.id is null;

select * from departments d left join employees  e on d.id=e.id_departments;
select d.id,d.name,e.id,e.name,e.id_departments from employees e right join departments  d on d.id=e.id_departments;

select * from teens t1  cross join teens t2
    where t1.gender<>t2.gender and t1.id < t2.id;;
