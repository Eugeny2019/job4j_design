dz ниже

CREATE TABLE companies(
    id int primary key,
    city text
);

CREATE TABLE goods(
    id int primary key,
    name text,
    company_id int references companies(id),
    price int
);

CREATE TABLE sales_managers(
    id int primary key,
    last_name text,
    first_name text,
    company_id int references companies(id),
    manager_fee int
);

CREATE TABLE managers(
    id int primary key,
    company_id int references companies(id)
);

INSERT INTO companies VALUES (1, 'Москва'),
                             (2, 'Нью-Йорк'),
                             (3, 'Мюнхен');

INSERT INTO goods VALUES (1, 'Небольшая квартира', 3, 5000),
                         (2, 'Квартира в центре', 1, 4500),
                         (3, 'Квартира у метро', 1, 3200),
                         (4, 'Лофт', 2, 6700),
                         (5, 'Загородный дом', 2, 9800);

INSERT INTO sales_managers VALUES (1, 'Доу', 'Джон', 2, 2250),
                                  (2, 'Грубер', 'Ганс', 3, 3120),
                                  (3, 'Смит', 'Сара', 2, 1640),
                                  (4, 'Иванов', 'Иван', 1, 4500),
                                  (5, 'Купер', 'Грета', 3, 2130);

INSERT INTO managers VALUES (1, 2),
                            (2, 3),
                            (4, 1);
							
							
SELECT * FROM sales_managers 
WHERE manager_fee > (SELECT AVG(manager_fee) FROM sales_managers);


SELECT name AS real_estate, price, (SELECT AVG(price) FROM goods) AS avg_price FROM goods;


SELECT AVG(manager_fee) 
FROM sales_managers WHERE sales_managers.id NOT IN (SELECT managers.id FROM managers);

SELECT city, (SELECT count(*) FROM goods as g 
WHERE c.id = g.company_id) as total_goods FROM companies c;

SELECT c.city, COUNT(g.name) AS total_goods FROM companies c 
    JOIN goods g ON c.id = g.company_id GROUP BY c.city;



SELECT last_name, first_name, manager_fee
FROM sales_managers sm1
WHERE sm1.manager_fee >= (SELECT AVG(manager_fee)
                            FROM sales_managers sm2
                            WHERE sm2.company_id = sm1.company_id);
							
							
SELECT company_id, AVG(price) AS average_price
FROM goods
GROUP BY company_id
HAVING AVG(price) > (SELECT MAX(price) FROM goods) / 2;


-- -----------------------------------------------------------------
-- dz

CREATE TABLE customers(
    id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);

insert into customers values (1, 'Вячеслав', 'Сербский', 23, 'Россия'),
							(2, 'Андрей', 'Крылов', 22, 'Россия'),
							(3, 'Аресний', 'Пивоваров', 35, 'Монголия'),
							(4, 'Сергей', 'Забросский', 40, 'Латвия'),
							(5, 'Светлана', 'Стебских', 28, 'Испания'),
							(6, 'Мирослава', 'Подлескова', 37, 'Грузия'),
							(7, 'Евгений', 'Явов', 45, 'Турция')
; 

select * from customers as c
where c.age = (select min(age) from customers);

CREATE TABLE orders(
    id serial primary key,
    amount int,
    customer_id int references customers(id)
);

insert into orders values (1, 15, 1),
							(2, 100, 2);
							
select c.id, c.first_name, c.last_name from customers as c 
where c.id not in (select customer_id from orders);




