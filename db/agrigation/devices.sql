create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name,price) values('iphone12',333.33);
insert into devices(name,price) values('iphone13',4444.44);
insert into devices(name,price) values('iphone14',55555.55);

insert into people(name) values('Adam');
insert into people(name) values('Bob');
insert into people(name) values('Dan');

insert into devices_people(device_id,people_id) values(1,1);
insert into devices_people(device_id,people_id) values(2,2);
insert into devices_people(device_id,people_id) values(3,3);
insert into devices_people(device_id,people_id) values(1,2);
insert into devices_people(device_id,people_id) values(1,3);
insert into devices_people(device_id,people_id) values(2,1);
insert into devices_people(device_id,people_id) values(2,3);

select avg(price) from devices;

select avg(price),p.name from people p inner join devices_people dp
                                        on p.id=dp.people_id
                                        inner join devices d
                                        on dp.device_id=d.id
group by p.name;

select avg(price) ,p.name from people p inner join devices_people dp
                                        on p.id=dp.people_id
                                        inner join devices d
                                        on dp.device_id=d.id
group by p.name
having avg(price)>5000;