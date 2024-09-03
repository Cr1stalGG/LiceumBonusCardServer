create table tickets(
    id uuid primary key,
    code varchar(12) unique not null,
    account_id uuid references accounts(id),
    bonus_id uuid references bonuses(id)
);