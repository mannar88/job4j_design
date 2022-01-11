create database test;
\c test

create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna (name, avg_age, discovery_date)
values ('test', 20000, date '2001-01-01');

insert into fauna (name, avg_age, discovery_date)
values ('testfish', 30000, date '1988-01-01');

insert into fauna (name, avg_age, discovery_date)
values('abc_text', 20999, date'2020-05-22');

insert into fauna (name, avg_age, discovery_date)
values('ttest_null', 1, null);

insert into fauna (name, avg_age, discovery_date)
values('test_abc', 5566, date'1921--11-19');