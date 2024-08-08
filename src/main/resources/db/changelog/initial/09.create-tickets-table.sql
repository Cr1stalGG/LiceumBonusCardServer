create table tickets(
    id bigserial primary key,
    code varchar(12) unique not null,
    account_id bigint references accounts(id),
    bonus_id bigint references bonuses(id)
);