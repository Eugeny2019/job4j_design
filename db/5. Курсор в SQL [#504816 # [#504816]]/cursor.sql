create table products (
    id serial primary key,
    name varchar(50),
    count integer default 0,
    price integer
);

insert into products (name, count, price) VALUES ('product_1', 1, 5);
insert into products (name, count, price) VALUES ('product_2', 2, 10);
insert into products (name, count, price) VALUES ('product_3', 3, 15);
insert into products (name, count, price) VALUES ('product_4', 4, 20);
insert into products (name, count, price) VALUES ('product_5', 5, 25);
insert into products (name, count, price) VALUES ('product_6', 6, 30);
insert into products (name, count, price) VALUES ('product_7', 7, 35);
insert into products (name, count, price) VALUES ('product_8', 8, 40);
insert into products (name, count, price) VALUES ('product_9', 9, 45);
insert into products (name, count, price) VALUES ('product_10', 10, 50);
insert into products (name, count, price) VALUES ('product_11', 11, 55);
insert into products (name, count, price) VALUES ('product_12', 12, 60);
insert into products (name, count, price) VALUES ('product_13', 13, 65);
insert into products (name, count, price) VALUES ('product_14', 14, 70);
insert into products (name, count, price) VALUES ('product_15', 15, 75);
insert into products (name, count, price) VALUES ('product_16', 16, 80);
insert into products (name, count, price) VALUES ('product_17', 17, 85);
insert into products (name, count, price) VALUES ('product_18', 18, 90);
insert into products (name, count, price) VALUES ('product_19', 19, 95);
insert into products (name, count, price) VALUES ('product_20', 20, 100);

claims=# BEGIN;
BEGIN
claims=*# DECLARE
claims-*#     cursor_products scroll cursor for
claims-*#                         select * from products;
DECLARE CURSOR
claims=*# move backward 15 from cursor_products;
MOVE 0
claims=*# move forward 15 from cursor_products;
MOVE 15
claims=*# move last from cursor_products;
MOVE 1
claims=*# fetch from cursor_products;
 id | name | count | price
----+------+-------+-------
(0 rows)


claims=*# move forward 15 from cursor_products;
MOVE 0
claims=*# move backward 15 from cursor_products;
MOVE 15
claims=*# fetch from cursor_products;
 id |   name    | count | price
----+-----------+-------+-------
  7 | product_7 |     7 |    35
(1 row)


claims=*# move last from cursor_products;
MOVE 1
claims=*# fetch from cursor_products;
 id | name | count | price
----+------+-------+-------
(0 rows)


claims=*# move backward from cursor_products;
MOVE 1
claims=*# fetch from cursor_products;
 id | name | count | price
----+------+-------+-------
(0 rows)


claims=*# move backward from cursor_products;
MOVE 1
claims=*# move backward from cursor_products;
MOVE 1
claims=*# fetch from cursor_products;
 id |    name    | count | price
----+------------+-------+-------
 20 | product_20 |    20 |   100
(1 row)


claims=*# move backward 5 from cursor_products;
MOVE 5
claims=*# move backward 8 from cursor_products;
MOVE 8
claims=*# move backward 5 from cursor_products;
MOVE 5
claims=*# move first from cursor_products;
MOVE 1
claims=*# fetch from cursor_products;
 id |   name    | count | price
----+-----------+-------+-------
  2 | product_2 |     2 |    10
(1 row)


claims=*# move backward from cursor_products;
MOVE 1
claims=*# move backward from cursor_products;
MOVE 0
claims=*# fetch from cursor_products;
 id |   name    | count | price
----+-----------+-------+-------
  1 | product_1 |     1 |     5
(1 row)


claims=*# close cursor_products;
CLOSE CURSOR
claims=*# commit;
COMMIT
claims=#