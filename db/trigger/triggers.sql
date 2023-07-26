create table products(
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create table history_of_price(
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

CREATE OR REPLACE FUNCTION  plusn()
   returns trigger AS $$
   begin
update products
set price = price + price * 0.2
where id in (select id from inserted);
return new;
end;
$$
LANGUAGE 'plpgsql';

create or replace function befor_plusn()
    returns trigger as
$$
        begin
            new.price = new.price + new.price * 0.2
            return new;
        end;
$$
LANGUAGE 'plpgsql';

create or replace function add_history_of_price()
    returns trigger as
$$
        begin
            insert into history_of_price (name,price,date)
            values (new.name,new.price,current_date)
            return new;
        end;
$$
LANGUAGE 'plpgsql';


create trigger plusn
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure plusn();

create trigger befor_plus_nalog
    before insert on products
    for each row
    execute procedure befor_plusn();

create trigger add_after_history_of_price
    after insert on products
    for each row
    execute procedure add_history_of_price();

insert into products(name,producer,count,price) values('testproduct','testproducer',1,33.33);

select * from products;
select * from history_of_price;