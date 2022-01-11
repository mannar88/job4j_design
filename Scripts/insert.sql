insert into role (name) values('test_role');

insert into users(name, role_id) values ('test_user', 1);

insert into roles(name) values('test_roles');

insert into role_roles(role_id, roles_id) values (1, 1);

insert into category(name) values('test_category');

insert into state(name) values('test_state');

insert into item(name, users_id, category_id, state_id) values ('test_item', 1, 1, 1);

insert into comments(comment, item_id) values('test_comment', 1);

insert into attachs(name, item_id) values('test_attachs', 1);

