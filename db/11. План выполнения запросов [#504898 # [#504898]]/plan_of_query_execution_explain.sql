-- dz ниже

CREATE TABLE customer (
    first_name text,
    last_name text
);

CREATE TABLE employee (
    first_name text,
    last_name text
);

INSERT INTO customer
VALUES ('Иван', 'Иванов'),
       ('Петр', 'Сергеев'),
       ('Ирина', 'Бросова'),
       ('Анна', 'Опушкина'),
       ('Потап', 'Потапов');

INSERT INTO employee
VALUES ('Кристина', 'Позова'),
       ('Михаил', 'Кругов'),
       ('Анна', 'Опушкина'),
       ('Иван', 'Иванов'),
       ('Сергей', 'Петров');
	   
	   
	   
SELECT first_name, last_name
FROM customer
UNION
SELECT first_name, last_name
FROM employee;

SELECT first_name, last_name
FROM customer
UNION
SELECT first_name, last_name
FROM employee
ORDER BY first_name, last_name;

SELECT first_name, last_name
FROM customer
WHERE status = 'Active'
UNION
SELECT first_name, last_name
FROM employee
WHERE emp_status = 'Current'
ORDER BY first_name, last_name;


SELECT
    e.first_name,
    e.last_name,
    c.first_name,
    c.last_name
FROM employee e
         INNER JOIN customer c
                    ON e.first_name = c.first_name
                        AND e.last_name = c.last_name;
						
SELECT first_name, last_name
FROM customer
UNION ALL
SELECT first_name, last_name
FROM employee;


SELECT first_name, last_name
FROM customer
EXCEPT
SELECT first_name, last_name
FROM employee;

SELECT first_name, last_name
FROM customer
INTERSECT
SELECT first_name, last_name
FROM employee;

CREATE TABLE referrer (
    first_name text,
    last_name text
);

INSERT INTO referrer
VALUES ('Евгений', 'Онегин'),
       ('Петр', 'Сергеев'),
       ('Александр', 'Ожегов'),
       ('Анна', 'Опушкина'),
       ('Михаил', 'Кругов');
	   
SELECT first_name, last_name
FROM customer
UNION
SELECT first_name, last_name
FROM employee
UNION
SELECT first_name, last_name
FROM referrer
ORDER BY first_name, last_name;

SELECT first_name, last_name
FROM customer
UNION ALL
SELECT first_name, last_name
FROM employee
EXCEPT
SELECT first_name, last_name
FROM referrer
ORDER BY first_name, last_name;

SELECT first_name, last_name
FROM customer
UNION ALL
(SELECT first_name, last_name
FROM employee
EXCEPT
SELECT first_name, last_name
FROM referrer)
ORDER BY first_name, last_name;

-- ------------------------------------
-- dz

CREATE TABLE movie (
    id SERIAL PRIMARY KEY,
    name text,
    director text
);

CREATE TABLE book (
    id SERIAL PRIMARY KEY,
    title text,
    author text
);

INSERT INTO movie (name, director)
VALUES ('Марсианин', 'Ридли Скотт'),
       ('Матрица', 'Братья Вачовски'),
       ('Властелин колец', 'Питер Джексон'),
       ('Гарри Поттер и узник Азкабана', 'Альфонсо Куарон'),
       ('Железный человек', 'Джон Фавро');

INSERT INTO book (title, author)
VALUES ('Гарри Поттер и узник Азкабана', 'Джоан Роулинг'),
       ('Властелин колец', 'Джон Толкин'),
       ('1984', 'Джордж Оруэлл'),
       ('Марсианин', 'Энди Уир'),
       ('Божественная комедия', 'Данте Алигьери');
	   

select m.name from movie m
intersect
select b.title from book b;

select b.title from book b
except
select m.name from movie m;


(select m.name from movie m
except
select b.title from book b)
union 
(select b.title from book b
except
select m.name from movie m);




