create table tbl1 (
    id serial primary key,
    field1 text,
    field2 character(42)
);

create table tbl2 (
    id serial primary key,
    field1 text,
    field2 int,
    field3 int references tbl1(id) unique
);

insert into tbl1 (field1, field2) values ('sfdgsdg', 'fgsfdg');
insert into tbl1 (field1, field2) values ('sfdgs3245sdg', 'fgsfadsdfdg');
insert into tbl1 (field1, field2) values ('sfdgdgfbn4sdg', 'fgs4wthrbsgdfdg');

insert into tbl2 (field1, field2, field3) values ('sfdgsdfresdg', 5, 1);
insert into tbl2(field1, field2, field3) values ('sfdgs32453gr5sdg', 7, 2);

select t1.field1 as Name, t2.field1 as Birthday from tbl1 as t1 
	join tbl2 as t2 on t1.id = t2.field3;
select t1.field1 as Name, t2.field1 as Birthday from tbl1 as t1 
	join tbl2 as t2 on t1.id != t2.field3;
select t1.field1 as Name, t2.field1 as Birthday from tbl1 as t1 
	join tbl2 as t2 on t2.field3 is not null;
