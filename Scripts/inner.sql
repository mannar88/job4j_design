create table inner_a( id serial primary key,
name text);

create table inner_b (id serial primary key,
number int,
inner_a_id int references inner_a (id));

insert into inner_a (name)
values ('test_01');
insert into inner_a (name)
values ('test_02');
insert into inner_a (name)
values ('test_03');

insert into inner_b(number)
values (0001);
insert into inner_b(number, inner_a_id)
values (0003, 3);

select*from inner_b join
inner_a a
on inner_b.inner_a_id=a.id;

select b.number, a.name from
inner_b as b join
inner_a as a on
b.inner_a_id=a.id;


select b.number as номер, a.name as имя from
inner_b as b join
inner_a as a on
b.inner_a_id=a.id;