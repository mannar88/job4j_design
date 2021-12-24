create database test;
\conect test;
create table users (Id serial primary key, Name character varying(30), Age integer,  Bool boolean);
insert into users (Name, Age, Bool) values ('Tom', 33, false);
select * from users;
delete from  users;