create table type(
id serial primary key,
name varchar(20)
);

create table product(
id serial primary key,
name varchar(20),
type_id int references type(id),
expired_date date,
price float
);

insert into type(name) values('заморозка');
insert into type(name) values('молочные продукты');
insert into type(name) values('вкусняшки');
insert into type(name) values('СЫР');
insert into type(name) values('МОЛОКО');

insert into product (name,type_id,expired_date,price) values('сыр Костромской',4,to_date('17 Jul 2023', 'DD Mon YYYY'),333.33);
insert into product (name,type_id,expired_date,price) values('масло',2,to_date('18 Jul 2023', 'DD Mon YYYY'),222.33);
insert into product (name,type_id,expired_date,price) values('МОЛОКО питьевое',5,to_date('13 Jul 2023', 'DD Mon YYYY'),111.33);
insert into product (name,type_id,expired_date,price) values('мороженое1',1,to_date('14 Jul 2023', 'DD Mon YYYY'),55.33);
insert into product (name,type_id,expired_date,price) values('мороженое2',2,to_date('16 Jul 2023', 'DD Mon YYYY'),323.55);
insert into product (name,type_id,expired_date,price) values('мороженое3',3,to_date('17 Jul 2023', 'DD Mon YYYY'),555.55);

select * from type t inner join product p on t.id=p.type_id
where t.name like 'СЫР';

select * from product where name like '%мороженое%';

select * from product where expired_date<CURRENT_DATE;

select * from product
where price=(select MAX(price) from product);

select tname,count(tname)  from
(select t.name tname,p.name pname from type t inner join product p on t.id=p.type_id) tt
group by tname;

select * from type t inner join product p on t.id=p.type_id
where t.name like 'СЫР' OR like 'МОЛОКО';

select tname,count(tname)  from
(select t.name tname,p.name pname from type t inner join product p on t.id=p.type_id) tt
group by tname
having count(tname)<10;

select p.name pname,t.name tname from type t inner join product p on t.id=p.type_id;