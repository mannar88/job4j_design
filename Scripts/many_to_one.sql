create table animal_kingdom(
    id serial primary key,
    name varchar(255)
);

create table kind_of_animal (
    id serial primary key,
    name varchar(255),
    position_id int references animal_kingdom(id)
);

insert into animal_kingdom(name) values ('mammals ');
insert into kind_of_animal (name, position_id) VALUES ('cat',1);

select * from kind_of_animal; 

select * from animal_kingdom where id in (select id from kind_of_animal);