create table cards(
    id bigserial primary key,
    number varchar(16) unique not null,
    balance int default 0 check(balance >= 0)
);