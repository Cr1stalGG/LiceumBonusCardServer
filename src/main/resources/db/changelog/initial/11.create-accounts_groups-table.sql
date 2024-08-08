create table accounts_groups(
    id bigserial primary key,
    account_id bigint references accounts(id),
    group_id bigint references groups(id)
);