create table accounts_groups(
    id bigserial primary key,
    account_id uuid references accounts(id),
    group_id uuid references groups(id)
);