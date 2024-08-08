create table roles(
    id bigserial primary key,
    name varchar(30) unique not null,
    description text
);