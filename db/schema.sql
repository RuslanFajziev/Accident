CREATE TABLE accident (
    id      serial primary key,
    name    varchar(2000),
    text    varchar(2000),
    address varchar(2000)
);

CREATE TABLE AccidentType (
    id serial primary key,
    name varchar(2000)
);

CREATE TABLE Rule (
    id serial primary key,
    name varchar(2000)
);