create table transactions(
    id bigserial primary key,
    balance int default 1 check(balance > 0),
    card_id bigint references cards(id),
    transaction_status_id bigint references transaction_statuses(id),
    message text not null,
    transaction_time timestamp not null
);