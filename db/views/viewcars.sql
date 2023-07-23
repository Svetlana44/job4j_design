create  table car_bodies (
id serial primary key,
name varchar(25)
);

create  table car_engines (
id serial primary key,
name varchar(25)
);

create  table car_transmissions(
id serial primary key,
name varchar(25)
);

create  table cars(
id serial primary key,
name varchar(25),
body_id int references car_bodies(id),
engine_id int references car_engines(id),
transmission_id int references car_transmissions(id)
);

insert into car_bodies(name) values('reno');
insert into car_bodies(name) values('pegot');
insert into car_bodies(name) values('chevrolet');
insert into car_bodies(name) values('daster');
insert into car_bodies(name) values('niva');

insert into car_engines(name) values('engine1');
insert into car_engines(name) values('engine2');
insert into car_engines(name) values('engine3');
insert into car_engines(name) values('engine4');

insert into car_transmissions(name) values('transmission1');
insert into car_transmissions(name) values('transmission2');
insert into car_transmissions(name) values('transmission3');
insert into car_transmissions(name) values('transmission4');

insert into cars(name,body_id,engine_id,transmission_id) values('car1',1,1,1);
insert into cars(name,body_id,engine_id,transmission_id) values('car2',null ,2,2);
insert into cars(name,body_id,engine_id,transmission_id) values('car3',3,null ,3);
insert into cars(name,body_id,engine_id,transmission_id) values('car4',4,4,null );
insert into cars(name,body_id,engine_id,transmission_id) values('car5',null ,1,null );
insert into cars(name,body_id,engine_id,transmission_id) values('car6',3,4,1);

create view all_chevrolets as
select c.id, c.name car_name, cb.name car_body, ce.name car_engine, ct.name car_transmission from cars c
left join car_bodies cb on c.body_id=cb.id
left join car_engines ce on c.engine_id=ce.id
left join car_transmissions ct on c.transmission_id=ct.id
where cb.name like 'chevrolet';

select * from all_chevrolets where car_transmission like 'transmission3';

