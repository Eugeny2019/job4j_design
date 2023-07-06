create table table1 (
    id serial primary key,
    name varchar(50)
);

insert into table1 (name) values ('sfthgsdfgsg');
insert into table1 (name) values ('erutjyrwsfghsf');
insert into table1 (name) values ('etsrtg');

create table table2 (
    id serial primary key,
    param1 int,
	param2 text
);

insert into table2 (param1, param2) values (123, 'asdfhehfdsnfhn');
insert into table2 (param1, param2) values (1, 'dfhnf');
insert into table2 (param1, param2) values (5, 'dfhndfn');


create table table3 (
    id serial primary key,
    name varchar(50)
);

insert into table3 (name) values ('sfthgsdfgsg');
insert into table3 (name) values ('erutjyrwsfghsf');
insert into table3 (name) values ('etsrtg');
insert into table3 (name) values ('sfthgsdfgsg');

create table table4 (
    id serial primary key,
    param1 int,
	param2 text,
	param_id integer references table3(id)
);

insert into table4 (param1, param2, param_id) values (123, 'asdfhehfdsnfhn', 1);
insert into table4 (param1, param2, param_id) values (123, 'asdfhehfdsnfhn', 2);
insert into table4 (param1, param2, param_id) values (123, 'asdfhehfdsnfhn', 3);
insert into table4 (param1, param2, param_id) values (123, 'asdfhehfdsnfhn', 1);

create table table5 (
    id serial primary key,
    param1 varchar(15),
    param_id integer references table2(id),
	param_id1 integer references table4(id)
);

insert into table5 (param1, param_id, param_id1) values ('gaddsfg', 1, 1);
insert into table5 (param1, param_id, param_id1) values ('dfgh', 2, 1);
insert into table5 (param1, param_id, param_id1) values ('dghmetm', 3, 3);
insert into table5 (param1, param_id, param_id1) values ('sdtjhwjrs', 1, 2);
insert into table5 (param1, param_id, param_id1) values ('rszsdytnt', 2, 1);


create view table20 as
select t1.name as name1, count(t3.name) as count, t3.name as name2 from table1 as t1
	join table2 t2 on t2.param1 = t1.id
	join table3 t3 on t3.name = t1.name
	join table4 t4 on t4.param_id = t3.id
	join table5 t5 on t5.param_id1 = t4.id and t5.param_id = t2.id
    group by (t1.name, t3.name) having count(t3.name) >= 2;
	
	