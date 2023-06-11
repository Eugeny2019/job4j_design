create table author (
    id serial primary key,
    name text
);

create table compose (
    id serial primary key,
    name text,
    author_id int references author(id)
);

create table music_lover (
    id serial primary key,
    name text
);

create table music_lover_compose (
    id serial primary key,
    music_lovel_id int references music_lover(id),
    compose_id int references compose(id)
);