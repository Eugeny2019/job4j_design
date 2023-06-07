create table birds(
id serial primary key,
name varchar(50),
size varchar(6),
description text
);

insert into birds (name, size, description) values ('angry', 'small', 'little angry bird');
select * from birds;
update birds set size = 'middle';
delete from birds;