/*
Связи между таблицами такие:

users - roles = many-to-one (у пользователя есть роли)
roles - rules = many-to-many (у ролей есть права)
items - users = many-to-one (у пользователя есть заявки)
items - comments = one-to-many (у заявки есть комментарии)
items - attachs = one-to-many (у заявки есть приложенные файлы)
items - categories = many-to-one (у заявки есть категории)
items - states = many-to-one (у заявки есть состояния)

"users - roles = many-to-one (у пользователя есть роли)"
 Ссылки references должны быть расположены со стороны many
*/

create table roles(
id serial primary key,
name varchar(20)
);

create table rules(
id serial primary key,
name varchar(20)
);

create table roles_rules(
id serial primary key,
roles_id int references roles(id),
rules_id int references rules(id)
);

create table users (
id serial primary key,
name varchar(20),
nikename varchar(15),
roles_id int references roles (id)
);

create table categories(
id serial primary key,
name varchar(20)
);

create table states(
id serial primary key,
name varchar(20)
);

create table items(
id serial primary key,
name varchar(20),
users_id int references users (id),
category_id int references categories (id),
states_id int references states (id)
);

create table comments(
id serial primary key,
text varchar(100),
items_id int references items (id)
);

create table attachs(
id serial primary key,
files varchar(50),
items_id int references items (id)
);