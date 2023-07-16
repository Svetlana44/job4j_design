/*
Сначала надо заполнять таблицы, в которых нет ссылок.
*/

insert into roles(name,users_id) values ('admin',1);

insert into rules(name) values ('rwe');

insert into roles_rules(roles_id,rules_id) values (1,1);

insert into users(name,nikename,roles_id) values ('Andersen','Neo',1);

insert into categories(name) values ('extra');

insert into states(name) values ('done');

insert into items(name,users_id,category_id,states_id) values ('itemName',1,1,1);

insert into comments(text,item_id) values ('comment for item',1);

insert into attachs(files,item_id) values ('item.txt',1);

