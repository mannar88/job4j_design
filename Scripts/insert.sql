insert into users(name) values ('test');

insert into role (name, users_id) values('test_role', 1);

insert into roles(name) values('test_roles');

insert into role_roles(role_id, roles_id) values (1, 1);

insert into item(name, users_id) values ('test_item', 1);

insert into comments(comment, item_id) values('test_comment', 1);

insert into attachs(name, item_id) values('test_attachs', 1);

insert into category(name, item_id) values('test_category', 1);

insert into state(name, item_id) values('test_state', 1);