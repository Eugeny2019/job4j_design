//many-to-many
create table people(
id serial primary key,
name varchar(255)
);
 
create table car(
id serial primary key,
name varchar(255)
);
 
create table people_cars(
id serial primary key,
people_id int references people(id),
car_id int references car(id)
);

insert into people(name) values ('Ivan');
insert into people(name) values ('Kirill');
insert into people(name) values ('Roman');

insert into car(name) values ('Toyota');
insert into car(name) values ('Honda');
insert into car(name) values ('Suzuki');

insert into people_cars(people_id, car_id) values (1, 1);
insert into people_cars(people_id, car_id) values (1, 2);
insert into people_cars(people_id, car_id) values (1, 3);
insert into people_cars(people_id, car_id) values (2, 1);
insert into people_cars(people_id, car_id) values (2, 2);
insert into people_cars(people_id, car_id) values (3, 3);


//many-to-one
create table forein_passport(
id serial primary key,
seria int,
number int
);

create table people1(
id serial primary key,
name varchar(255),
passport_id int references forein_passport(id)
);

insert into forein_passport(seria, number) values (70, 12345678);
insert into forein_passport(seria, number) values (70, 43214359);
insert into forein_passport(seria, number) values (70, 76455454);
insert into forein_passport(seria, number) values (70, 56875483);

insert into people1(name, passport_id) values ('Vasia', 1);
insert into people1(name, passport_id) values ('Vasia', 2);
insert into people1(name, passport_id) values ('Victor', 3);
insert into people1(name, passport_id) values ('Andrei', 4);


//one-to-one
create table tabNo(
id serial primary key,
number int
);

create table people2(
id serial primary key,
name varchar(255),
tabNo_id int references tabNo(id) unique
);

insert into tabNo(number) values (1);
insert into tabNo(number) values (2);
insert into tabNo(number) values (10);
insert into tabNo(number) values (20);

insert into people2(name, tabNo_id) values ('Vasia', 1);
insert into people2(name, tabNo_id) values ('Maksim', 2);
insert into people2(name, tabNo_id) values ('Victor', 3);
insert into people2(name, tabNo_id) values ('Andrei', 4);

select * from tabNo;
select * from people2;
