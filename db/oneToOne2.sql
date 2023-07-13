create table instrument(
    id serial primary key,
    name varchar(20),
    power int
);

create table fixik(
    id serial primary key,
    name varchar(255),
    instrument_id int references instrument(id) unique
);