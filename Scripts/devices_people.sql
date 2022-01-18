create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values('device_01', 1000);
insert into devices(name, price) values('device_02', 5000);
insert into devices(name, price) values('device_03', 10000);

insert into people (name) values('people_01');
insert into people (name) values('people_02');
insert into people (name) values('people_03');

insert into devices_people(device_id, people_id) values(1, 1);
insert into devices_people(device_id, people_id) values(2, 2);
insert into devices_people(device_id, people_id) values(3, 3)
insert into devices_people(device_id, people_id) values(2, 3);;

select avg (price) from devices;

select p.name as Имя, avg (d.price)
from people as p
join devices_people
on devices_people.people_id=p.id
join devices as d
on devices_people.device_id=d.id
group by p.name;

select p.name as Имя, avg (d.price)
from people as p
join devices_people
on devices_people.people_id=p.id
join devices as d
on devices_people.device_id=d.id
group by p.name
having avg(d.price)>5000;