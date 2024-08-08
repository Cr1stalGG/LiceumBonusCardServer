create table accounts_transactions(
    id bigserial primary key,
    account_id bigint references accounts(id),
    transaction_id bigint references transactions(id)
);