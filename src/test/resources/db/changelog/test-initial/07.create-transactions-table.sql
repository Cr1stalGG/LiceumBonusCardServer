create table transactions(
    id uuid primary key,
    balance int default 1 check(balance > 0),
    card_id uuid references cards(id),
    transaction_status_id uuid references transaction_statuses(id),
    message text not null,
    transaction_time timestamp not null
);