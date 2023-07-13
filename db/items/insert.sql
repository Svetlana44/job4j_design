insert into users(name,nikename) values ('Andersen','Neo');

insert into roles(name,users_id) values ('admin',1);

insert into rules(name) values ('rwe');

insert into rules(roles_id,rules_id) values (1,1);

insert into items(name,users_id,category_id) values ('itemName',1,1);

insert into comments(text,item_id) values ('comment for item',1);

insert into attachs(files,item_id) values ('item.txt',1);

insert into states(name,item_id) values ('done',1);

insert into categories(name) values ('extra');