create table fone(id serial primary key,
number varchar(255)
);
create table users( id serial primary key,
name varchar(255)
);

create table fone_users ( id serial primary key,
number_id int references fone(id) unique,
    users_id int references users(id) unique
);

insert into fone(number) values ('+79222223344');


insert into users (name) values ('Ivan');

insert into fone_users(number_id, users_id) values (1, 1);