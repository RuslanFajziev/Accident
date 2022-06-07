CREATE TABLE Accident_Type (
    id serial primary key,
    name varchar(2000) not null
);

insert into Accident_Type (name) values ('Две машины');
insert into Accident_Type (name) values ('Машина и человек');
insert into Accident_Type (name) values ('Машина и велосипед');

CREATE TABLE Rule (
    id serial primary key,
    name varchar(2000) not null
);

insert into Rule (name) values ('Статья. 1');
insert into Rule (name) values ('Статья. 2');
insert into Rule (name) values ('Статья. 3');

CREATE TABLE Accident (
    id      serial primary key,
    name    varchar(2000) not null,
    text    varchar(2000) not null,
    address varchar(2000) not null,
    accident_type_id int           not null references Accident_Type (id)
);

create table Accident_Rule (
    accident_id integer not null references accident (id),
    rules_id    integer not null references rule (id),
    constraint accident_rule_pkey primary key (accident_id, rules_id)
);