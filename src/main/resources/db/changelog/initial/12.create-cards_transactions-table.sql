create table cards_transactions(
    id bigserial primary key,
    card_id bigint references cards(id),
    transaction_id bigint references transactions(id)
);