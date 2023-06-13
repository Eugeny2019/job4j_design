insert into users (name) values ('Igor');
insert into users (name) values ('Evgenii');
insert into users (name) values ('Andrei');
insert into users (name) values ('Vitalii');
insert into users (name) values ('Roman');

select * from users;

insert into states (state) values ('Draft');
insert into states (state) values ('In progress');
insert into states (state) values ('Finished');

select * from states;

insert into rules (rule) values ('Read');
insert into rules (rule) values ('Write');

select * from rules;

insert into roles (role,users_id) values ('Admin',1);
insert into roles (role,users_id) values ('User',2);
insert into roles (role,users_id) values ('User',3);
insert into roles (role,users_id) values ('Admin',4);
insert into roles (role,users_id) values ('Admin',5);

select * from roles;

insert into roles_rules (roles_id,rules_id) values (1,1);
insert into roles_rules (roles_id,rules_id) values (1,2);
insert into roles_rules (roles_id,rules_id) values (2,1);
insert into roles_rules (roles_id,rules_id) values (3,1);
insert into roles_rules (roles_id,rules_id) values (4,1);
insert into roles_rules (roles_id,rules_id) values (4,2);
insert into roles_rules (roles_id,rules_id) values (5,1);
insert into roles_rules (roles_id,rules_id) values (5,2);

select * from roles_rules;

insert into categories (category) values ('urgent');
insert into categories (category) values ('not urgent');
insert into categories (category) values ('important');
insert into categories (category) values ('not important');

select * from categories;


insert into items (subject,description,users_id,categories_id,states_id)
	values ('bug on the flat','Big bug on the flat',2,1,1);
insert into items (subject,description,users_id,categories_id,states_id)
	values ('set printer','set printer in room 409',3,2,2);
insert into items (subject,description,users_id,categories_id,states_id)
	values ('change monitor','change monitor in room 101',3,3,2);

select * from items;

insert into comments (comment,items_id) values ('take', 2);
insert into comments (comment,items_id) values ('go', 2);
insert into comments (comment,items_id) values ('done', 1);
insert into comments (comment,items_id) values ('go', 3);

select * from comments;

insert into attachs (attachment,items_id) values ('file.txt', 1);
insert into attachs (attachment,items_id) values ('default.txt', 2);
insert into attachs (attachment,items_id) values ('in.txt', 3);

select * from attachs;
