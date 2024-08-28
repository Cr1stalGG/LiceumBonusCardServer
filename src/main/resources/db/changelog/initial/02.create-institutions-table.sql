create table institutions(
    id bigserial primary key,
    name varchar unique not null,
    city varchar(60) not null
    --todo add some info
);