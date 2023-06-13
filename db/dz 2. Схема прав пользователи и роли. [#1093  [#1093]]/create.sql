create table users(
    id serial primary key,
	name text
);

create table roles(
    id serial primary key,
	role text,
	users_id int references users(id)
);

create table rules(
    id serial primary key,
	rule text
);

create table roles_rules(
    id serial primary key,
	roles_id int references roles(id),
	rules_id int references rules(id)
);

create table states(
    id serial primary key,
	state text
);

create table categories(
    id serial primary key,
	category text
);

create table items(
    id serial primary key,
	subject character(100),
	description text,
	users_id int references users(id),
	categories_id int references categories(id),
	states_id int references states(id)
);


create table comments(
    id serial primary key,
	comment text,
	items_id int references items(id)
);

create table attachs(
    id serial primary key,
	attachment text,
	items_id int references items(id)
);