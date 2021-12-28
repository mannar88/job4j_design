 create table цвет(
     id serial primary key,
     name varchar(255)
 ); 
 create table фрукты(
     id serial primary key,
     name varchar(255)
 );
 create table цвет_фрукт(
     id serial primary key,
     цвет_id int references цвет(id),
     фрукт_id int references фрукты(id)
 );

insert into цвет(name) values ('Зеленый');
insert into цвет(name) values ('Красный');
insert into цвет(name) values ('Желтый');

insert into фрукты(name) values ('Яблоко');
insert into фрукты(name) values ('Банан');
insert into фрукты(name) values ('Киви');

insert into цвет_фрукт (цвет_id, фрукт_id) values(1,1);
insert into цвет_фрукт (цвет_id, фрукт_id) values(2,1);
insert into цвет_фрукт (цвет_id, фрукт_id) values(3,1);
insert into цвет_фрукт (цвет_id, фрукт_id) values(1,2);
insert into цвет_фрукт (цвет_id, фрукт_id) values(3,2);
insert into цвет_фрукт (цвет_id, фрукт_id) values(1,3);