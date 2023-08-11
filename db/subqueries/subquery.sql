CREATE TABLE customers(
    id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);

insert into customers(first_name,last_name,age,country) values('Nick','Bond',28,'PG');
insert into customers(first_name,last_name,age,country) values('Jim','Bond',6,'PG');
insert into customers(first_name,last_name,age,country) values('Mary','Bond',26,'PG');
insert into customers(first_name,last_name,age,country) values('Pol','Geer',23,'US');
insert into customers(first_name,last_name,age,country) values('Jery','Ras',16,'GB');

select * from customers where age=(select MIN(age) from customers);

CREATE TABLE orders(
    id serial primary key,
    amount int,
    customer_id int references customers(id)
);

insert into orders(amount,customer_id) values(3,1);
insert into orders(amount,customer_id) values(33,1);
insert into orders(amount,customer_id) values(333,1);
insert into orders(amount,customer_id) values(33,3);
insert into orders(amount,customer_id) values(333,4);

select * from customers where id not in (select customer_id from orders);