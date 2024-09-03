create table transaction_statuses(
    id uuid primary key,
    name varchar(30) unique not null,
    description text not null
);