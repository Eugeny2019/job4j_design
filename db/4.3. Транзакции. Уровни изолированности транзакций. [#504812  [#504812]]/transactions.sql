-- # урок. дз ниже

create table person (
    id serial primary key,
    firstname varchar(50),
    lastname varchar(50),
    age integer
);

insert into person (firstname, lastname, age) values ('Vasiliy', 'Kozhemiako', 22);
insert into person (firstname, lastname, age) values ('Sergey', 'Bergman', 35);
insert into person (firstname, lastname, age) values ('Anna', 'Brusova', 33);








