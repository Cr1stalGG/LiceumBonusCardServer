create table transactions(
    id bigserial primary key,
    balance int default 1 check(balance > 0),
    from_card_id varchar(16) references cards(number),
    to_card_id varchar(16) references cards(number),
    transaction_time timestamp not null
);