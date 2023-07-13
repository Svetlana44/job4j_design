create table emploeys(
     id serial primary key,
     name varchar(255)
 );

 create table offices(
     id serial primary key,
     name varchar(255)
 );

 create table emploeys_offices(
     id serial primary key,
     emploeys_id int references emploeys(id),
     offices_id int references offices(id)
 );