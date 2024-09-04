create table roles(
    id uuid primary key,
    name varchar(30) unique not null,
    description text
);