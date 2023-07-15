create TABLE home(
id serial primary key,
place varchar(25)
);

create TABLE fixiks(
id serial primary key,
name varchar(15),
color varchar(10),
age int(),
home_id references home(id)
);