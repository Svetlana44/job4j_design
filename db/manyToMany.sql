create table students(
     id serial primary key,
     name varchar(255)
 );

 create table courses(
     id serial primary key,
     name varchar(255)
 );

 create table students_courses(
     id serial primary key,
     student_id int references students(id),
     course_id int references courses(id)
 );