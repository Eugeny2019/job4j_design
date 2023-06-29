create table owners(
    id serial primary key,
    name varchar(255)
);

create table devices(
    id serial primary key,
    name varchar(255),
    owner_id int references owners(id)
);

insert into owners(name) values ('Owner 1');
insert into owners(name) values ('Owner 2');
insert into owners(name) values ('Owner 3');

insert into devices(name, owner_id) values ('Device 1', 1);
insert into devices(name, owner_id) values ('Device 2', 2);
insert into devices(name, owner_id) values ('Device 3', 3);
insert into devices(name, owner_id) values ('Device 4', null);
insert into devices(name, owner_id) values ('Device 5', null);
insert into devices(name, owner_id) values ('Device 6', 1);

select * from devices d left join owners o on d.owner_id = o.id;

select * from devices d left join owners o on d.owner_id = o.id where o.id is null;

select * from owners o left join devices d on o.id = d.owner_id;


select * from devices d left join owners o on d.owner_id = o.id;
select * from owners o right join devices d on d.owner_id = o.id;

select * from owners o left join devices d on o.id = d.owner_id;
select * from devices d right join owners o on d.owner_id = o.id;

select * from devices d full join owners o on d.owner_id = o.id;

select * from devices d left join owners o on d.owner_id = o.id
union
select * from devices d right join owners o on d.owner_id = o.id;

select * from devices d cross join owners o;



DZ
1.
create table employees(
    id serial primary key,
    name varchar(255)
);

create table departments(
    id serial primary key,
    name varchar(255),
	employees_id int references employees(id)
);

insert into employees(name) values ('Ivanov');
insert into employees(name) values ('Petrov');
insert into employees(name) values ('Sidorov');

insert into departments(name, employees_id) values ('department 1', 1);
insert into departments(name, employees_id) values ('department 2', 2);
insert into departments(name, employees_id) values ('department 3', 3);
insert into departments(name) values ('department 4');
insert into departments(name) values ('department 4');

2.
select * from employees e left join departments d on e.id = d.employees_id;
select * from departments d right join employees e on d.employees_id = e.id;


select * from employees e right join departments d on e.id = d.employees_id;
select * from departments d left join employees e on d.employees_id = e.id;

select * from employees e full join departments d on e.id = d.employees_id;

select * from employees e cross join departments;

3.
select * from departments d left join employees e on e.id = d.employees_id where d.employees_id is null;

4.
select e.*, d.* from employees e left join departments d on e.id = d.employees_id;
select e.*, d.* from departments d right join employees e on d.employees_id = e.id;

5.
create table teens(
    id serial primary key,
    name varchar(255),
	gender varchar(1)
);

insert into teens(name, gender) values ('Ivanov', 'M');
insert into teens(name, gender) values ('Petrov', 'M');
insert into teens(name, gender) values ('Sidorov', 'M');
insert into teens(name, gender) values ('Ivanova', 'F');
insert into teens(name, gender) values ('Petrova', 'F');
insert into teens(name, gender) values ('Sidorova', 'F');

select t1.name, t2.name  from teens t1 cross join teens t2 where t1.gender != t2.gender;








