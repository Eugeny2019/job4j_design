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

insert into devices (name, price) values ('HP Pavilion 550n', 1500);
insert into devices (name, price) values ('HP Pavilion 750n', 8000);
insert into devices (name, price) values ('Asus 2135n', 7000);
insert into devices (name, price) values ('Acer Ins 12r18tv2', 1200);
insert into devices (name, price) values ('MSI klmn', 1700);
insert into devices (name, price) values ('Logitech M3', 205);
insert into devices (name, price) values ('A4Tech', 18);
insert into devices (name, price) values ('HP Pavilion 750n', 2000);
insert into devices (name, price) values ('HP Pavilion 750n', 1900);

insert into people (name) values ('Viktor');
insert into people (name) values ('Vladimir');
insert into people (name) values ('Evgenii');
insert into people (name) values ('Pavel');
insert into people (name) values ('Andrei');

insert into devices_people (device_id, people_id) values (1, 1);
insert into devices_people (device_id, people_id) values (1, 2);
insert into devices_people (device_id, people_id) values (1, 3);
insert into devices_people (device_id, people_id) values (2, 2);
insert into devices_people (device_id, people_id) values (3, 3);
insert into devices_people (device_id, people_id) values (4, 4);
insert into devices_people (device_id, people_id) values (5, 5);

select name, avg(price) from devices group by name;

select p.name, avg(d.price) from devices_people dp
join devices d on (dp.device_id = d.id)
join people p on (dp.people_id = p.id)
group by p.name;

select p.name, avg(d.price) from devices_people dp
join devices d on (dp.device_id = d.id and d.price > 5000)
join people p on (dp.people_id = p.id)
group by p.name;

