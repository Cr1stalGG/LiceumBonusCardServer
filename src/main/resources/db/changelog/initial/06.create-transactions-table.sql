create table transactions(
    id bigserial primary key,
    balance int default 1 check(balance > 0),
    card_id bigint references cards(id),
    status_id bigint references statuses(id),
    message text not null,
    transaction_time timestamp not null
);