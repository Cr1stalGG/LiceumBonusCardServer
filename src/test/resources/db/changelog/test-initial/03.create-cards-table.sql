create table cards(
    id uuid primary key,
    number varchar(16) unique not null,
    balance int default 0 check(balance >= 0)
);