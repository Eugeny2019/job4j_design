DZ
1.
create table car_bodies(
    id serial primary key,
    name varchar(255)
);

create table car_engines(
    id serial primary key,
    name varchar(255)
);

create table car_transmissions(
    id serial primary key,
    name varchar(255)
);

create table cars(
    id serial primary key,
    name varchar(255),
    body_id int references car_bodies(id),
	engine_id  int references car_engines(id),
	transmission_id int references car_transmissions(id) 	
);

insert into car_bodies(name) values ('седан 10BS');
insert into car_bodies(name) values ('седан 11BS');
insert into car_bodies(name) values ('седан 20BS');
insert into car_bodies(name) values ('седан 31BS');
insert into car_bodies(name) values ('седан 45BS');
insert into car_bodies(name) values ('хэтчбек 08BS');
insert into car_bodies(name) values ('пикап 75BS');

insert into car_engines(name) values ('DD10');
insert into car_engines(name) values ('DR11');
insert into car_engines(name) values ('NR20');
insert into car_engines(name) values ('SR30');

insert into car_transmissions(name) values ('10_TR');
insert into car_transmissions(name) values ('11_TR');
insert into car_transmissions(name) values ('20_TR');
insert into car_transmissions(name) values ('30_TR');

insert into cars(name, body_id, engine_id, transmission_id) values ('10_TR', 1, 1, 1);
insert into cars(name, body_id, engine_id, transmission_id) values ('10_TR1', null, 1, 1);
insert into cars(name, body_id, engine_id, transmission_id) values ('11_TR', 2, 2, 2);
insert into cars(name, body_id, engine_id, transmission_id) values ('11_TR1', 2, null, 2);
insert into cars(name, body_id, engine_id, transmission_id) values ('20_TR', 3, 3, 3);
insert into cars(name, body_id, engine_id) values ('20_TR1', 3, 3);
insert into cars(name, body_id, engine_id, transmission_id) values ('10_TR', 1, 1, 1);
insert into cars(name, body_id, engine_id, transmission_id) values ('10_TR', 1, 1, 1);

2.
2.1.
select c.id, c.name car_name, cb.name body_name, ce.name engine_name, ct.name transmission_name from cars c
	left join car_bodies cb on cb.id = c.body_id
	left join car_engines ce on ce.id = c.engine_id
	left join car_transmissions ct on ct.id = c.transmission_id
;

2.2.
select c.id, c.name car_name, cb.name body_name from cars c
	right join car_bodies cb on cb.id = c.body_id
	where c.name is null
;

2.3
select c.id, c.name car_name, ce.name engine_name from cars c
	right join car_engines ce on ce.id = c.body_id
	where c.name is null
;

2.4
select c.id, c.name car_name, ct.name transmission_name from cars c
	right join car_transmissions ct on ct.id = c.body_id
	where c.name is null
;


