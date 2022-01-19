create table departments(id serial primary key,
name text);

create table emploers( id serial primary key,
name text,
department_id int references departments(id));

insert into departments(name) values ('department_1');
insert into departments(name) values ('department_2');
insert into departments(name) values ('department_3');
insert into departments(name) values ('department_4');
insert into emploers(name, department_id) values ('emploer_1',1);
insert into emploers(name, department_id) values ('emploer_2',2);
insert into emploers(name, department_id) values ('emploer_3',3);
insert into emploers(name) values ('emploer_4');
insert into emploers(name) values ('emploer_5');
insert into emploers(name, department_id) values ('emploer_6',1);

select*from emploers
left join departments  
on emploers.department_id=departments.id;

select*from emploers
right join departments  
on emploers.department_id=departments.id;

select*from emploers
full join departments  
on emploers.department_id=departments.id;


select*from emploers
cross join departments;

select*from departments   
left join emploers
on emploers.department_id=departments.id where emploers.department_id is null;

select*from emploers
left join departments  
on emploers.department_id=departments.id where emploers.department_id is not null;
select*from emploers
right join departments  
on emploers.department_id=departments.id where emploers.department_id is not null;

create table teens(id serial primary key,
name text,
gender text);
insert into teens  (name, gender) values ('Маша', 'Ж');
insert into teens  (name, gender) values ('Даша', 'Ж');
insert into teens  (name, gender) values ('Галя', 'Ж');
insert into teens  (name, gender) values ('Саша', 'М');
insert into teens  (name, gender) values ('Паша', 'М');
insert into teens  (name, gender) values ('Гоша', 'М');

select t1.name, t2.name
from teens as t1
cross join teens as t2
where t1.gender!= t2.gender; 