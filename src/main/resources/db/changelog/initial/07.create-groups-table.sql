create table groups(
    id bigserial primary key,
    admin_id bigint references accounts(id),
    name varchar(60) unique not null,
    description text
);