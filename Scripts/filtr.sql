create table type (id serial primary key,
name text);

create table product (id serial primary key,
name text,
type_id int references type(id),
expired_date date,
price float);

insert into type(name) values ('Молоко');
insert into type(name) values ('Фрукты');
insert into type(name) values ('Сыр');

insert into  product (name, type_id, expired_date , price)
values ('Молоко натуральное',1, date '2022-01-26', 67);
insert into  product (name, type_id, expired_date , price)
values ('Сливки',1, date '2022-01-16',80);
insert into  product (name, type_id, expired_date , price)
values ('Молоко сгущенное',1, date '2032-01-01', 90);
insert into  product (name, type_id, expired_date , price)
values('Сыр голландский',3,date '2022-01-01', 400);
insert into  product (name, type_id, expired_date , price)
values('Сыр-косичка к пиву',3,date '2023-01-01', 400);
insert into  product (name, type_id, expired_date , price)
values('Мандарины',2, date '2022-01-02', 120);

select*from product where type_id=(select type.id from type where name='Сыр');
select*from product where name LIKE '%мороженое%';
select*from product where expired_date<CURRENT_DATE;
select*from product where price=(select max(price) from product);
select t.name, count (t.name)
from type as t
join product
on t.id=product.type_id
group by t.name;
select*from product where type_id=(select type.id from type where name='Сыр' )
or type_id=(select type.id from type where name='Молоко');
select t.name, count (t.name)
from type as t
join product
on t.id=product.type_id
group by t.name
having count(t.name)<10;
select p.name as Продукт, t.name as Тип
from product as p
join type as t
on p.type_id=t.id;