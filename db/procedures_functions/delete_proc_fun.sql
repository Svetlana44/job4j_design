drop table products;


create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create or replace procedure insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
language 'plpgsql'
as $$
    BEGIN
    insert into products (name, producer, count, price)
    values (i_name, prod, i_count, i_price);
    END
$$;

call insert_data('product_1', 'producer_1', 5, 2);
call insert_data('product_2', 'producer_2', 15, 22);
call insert_data('product_3', 'producer_3', 35, 32);

create or replace procedure delete_data(i_id integer)
language 'plpgsql'
as $$
    BEGIN
    delete from products where id=i_id;
    END
$$;

call delete_data(13);

create or replace function f_delete_data(i_price integer)
returns void
language 'plpgsql'
as
$$
    begin
         delete from products where price < i_price;
    end;
$$;

select f_delete_data(10);

select * from products;