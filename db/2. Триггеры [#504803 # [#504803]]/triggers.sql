-- # урок. дз ниже

create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);


create or replace function discount()
    returns trigger as
$$
    BEGIN
        update products
        set price = price - price * 0.2
        where count <= 5 AND id = new.id;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger discount_trigger
    after insert
    on products
    for each row
    execute procedure discount();
	

insert into products (name, producer, count, price) VALUES ('product_3', 'producer_3', 8, 115);

insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);

select * from products;

alter table products disable trigger discount_trigger;

insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);

drop trigger discount_trigger on products;


create or replace function tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price - price * 0.2
        where id = (select id from inserted) and count <= 5;
        return new;
    END;
$$
LANGUAGE 'plpgsql';


create	 trigger tax_trigger
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure tax()
;


-- # дз

create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);


-- 1.
create or replace function tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.05
        where id = (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';


create	 trigger tax_trigger
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure tax()
;

insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);
insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);
select * from products;

drop trigger discount_trigger on products;


-- 2.
-- drop trigger tax_trigger on products;

create or replace function tax_before()
    returns trigger as
$$
    BEGIN
		new.price = new.price + new.price * 0.05; 
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger
    before insert on products
    for each row
    execute procedure tax_before();

insert into products (name, producer, count, price) VALUES ('product_2', 'producer_1', 3, 100);
insert into products (name, producer, count, price) VALUES ('product_2', 'producer_1', 3, 100);
select * from products;


-- 3.
create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);


create or replace function log_history()
    returns trigger as
$$
    BEGIN
		insert into history_of_price (name, price, date) values (new.name, new.price, CURRENT_DATE);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger log_history
    after insert on products
    for each row
    execute procedure log_history();

insert into products (name, producer, count, price) VALUES ('product_2', 'producer_1', 3, 100);
select * from products;
select * from history_of_price;









