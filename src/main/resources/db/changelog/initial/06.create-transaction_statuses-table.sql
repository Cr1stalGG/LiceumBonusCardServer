create table transaction_statuses(
    id bigserial primary key,
    name varchar(30) unique not null,
    description text not null
);