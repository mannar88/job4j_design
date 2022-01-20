create table кузов(id serial primary key,
name text);
create table двигатель (id serial primary key,
name text);
create table кпп (id serial primary key,
name text);
create table car (id serial primary key,
name text,
кузов_id int references кузов(id),
двигатель_id int references двигатель(id),
кпп_id int references кпп(id));

insert into кузов(name) values ('Седан');
insert into кузов(name) values ('Универсал');
insert into кузов(name) values ('Хэчбэк');
insert into двигатель (name) values ('Бензиновый ДВС');
insert into двигатель (name) values ('ИнжекторныйДВС');
insert into двигатель (name) values ('Дизельный ДВС');
insert into кпп (name) values('Робот');
insert into кпп (name) values('Автомат');
insert into кпп (name) values('Механика');
insert into car (name, кузов_id, двигатель_id, кпп_id)
values('car_1', 1,1,2);
insert into car (name, кузов_id, двигатель_id, кпп_id)
values('car_2', 1,1,3);
insert into car (name, кузов_id, двигатель_id, кпп_id)
values('car_3', 1,3,1);
insert into car (name, кузов_id, двигатель_id, кпп_id)
values('car_4', 1,3,2);
insert into car (name, кузов_id, двигатель_id, кпп_id)
values('car_5', 2,1,1);
insert into car (name, кузов_id, двигатель_id, кпп_id)
values('car_6', 2,3,3);

select car.name as Машина, кузов.name Кузов, двигатель.name as Движок, кпп.name as Коробка
from car
join кузов
on car.кузов_id=кузов.id
join двигатель
on car.двигатель_id=двигатель.id
join кпп
on car.кпп_id=кпп.id;

select кузов.name from кузов
left join car
on кузов.id =car.кузов_id
where car.name is null;

select двигатель.name from двигатель
left join car
on двигатель.id =car.двигатель_id
where car.name is null;

select кпп.name from кпп
left join car
on кпп.id =car.кпп_id
where car.name is null;