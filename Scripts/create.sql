create table users ( id serial primary key,
name text,
role_id int references role(id));

create table role( id serial primary key,
name text);

create table roles (id serial primary key,
 name text);

create table role_roles(id serial primary key,
roles_id int references roles (id),
role_id int references role (id));

create table item (id serial primary key,
name text,
users_id int references users(id),
category_id int references category (id),
state_id int references state(id));

create table comments ( id serial primary key,
comment text,
item_id int rferences item(id));

create table attachs ( id serial primary key,
name text,
item_id int references item (id));

create table category  ( id serial primary key,
name text);

create table state  ( id serial primary key,
name text);