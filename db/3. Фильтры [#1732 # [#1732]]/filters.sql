create table product(
    id serial primary key,
    name varchar(255),
	type_id int references type(id),
	expired_date date,
    price float
);

create table type(
    id serial primary key,
    name varchar(255)
);

insert into type (name) values ('сыр');
insert into type (name) values ('молоко');
insert into type (name) values ('мороженое');
insert into type (name) values ('конфеты');
insert into type (name) values ('овощи');


insert into product (name, type_id, expired_date, price) values ('Сыр гауда', 1, '06.20.2023', 700);
insert into product (name, type_id, expired_date, price) values ('Сыр гауда', 1, '06.27.2023', 710);
insert into product (name, type_id, expired_date, price) values ('Сыр гауда', 1, '07.01.2023', 720);
insert into product (name, type_id, expired_date, price) values ('Сыр плавленный', 1, '06.25.2023', 100);
insert into product (name, type_id, expired_date, price) values ('Сыр моцарелла', 1, '07.02.2023', 900);
insert into product (name, type_id, expired_date, price) values ('Сыр царский', 1, '08.05.2023', 1400);
insert into product (name, type_id, expired_date, price) values ('Молоко стремное', 2, '09.15.2023', 100);
insert into product (name, type_id, expired_date, price) values ('Молоко ни че так', 2, '11.18.2023', 82);
insert into product (name, type_id, expired_date, price) values ('Молоко ага вкусное', 2, '06.14.2023', 120);
insert into product (name, type_id, expired_date, price) values ('Мороженое ванильное', 3, '08.07.2023', 20);
insert into product (name, type_id, expired_date, price) values ('Мороженое сливочное', 3, '01.23.2024', 30);
insert into product (name, type_id, expired_date, price) values ('Лук', 5, '08.01.2024', 30);

select * from product;


select p.name, p.expired_date, p.price from product p, type t where p.type_id=t.id and t.name='сыр';

select p.name, p.expired_date, p.price from product p where lower(p.name) like '%мороженое%';

select p.name, p.expired_date, p.price from product p where p.expired_date < CURRENT_DATE;

select p.name, max(p.price) from product p
group by p.name;

select t.name, count(t.name) from type t
join product p on (t.id=p.type_id)
group by t.name;

select p.name, p.expired_date, p.price from product p, type t where p.type_id=t.id and t.name in('сыр','молоко');

select t.name, count(t.name) as "pcs" from type t
join product p on (t.id=p.type_id)
group by t.name
having count(t.name) < 10;

select p.name as "Название продукта", t.name as "Тип продукта" from product p, type t
where p.type_id=t.id;

