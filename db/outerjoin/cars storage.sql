create  table car_bodies(
id serial primary key,
name varchar(25)
);

create  table car_engines(
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

select c.id, c.name, cb.name, ce.name, ct.name from cars c
left join car_bodies cb ON c.body_id=cb.id
left join car_engines ce ON c.engine_id=ce.id
left join car_transmissions ct ON c.transmission_id=ct.id;

select cb.name from cars c
right join car_bodies cb ON c.body_id=cb.id
where c.name is null;

select ce.name from cars c
right join car_engines ce ON c.engine_id=ce.id
where c.name is null;

select ct.name from cars c
right join car_transmissions ct ON c.transmission_id=ct.id
where c.name is null;
