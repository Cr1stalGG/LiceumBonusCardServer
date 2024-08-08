create table accounts_activities(
    id bigserial primary key,
    account_id bigint references accounts(id),
    activity bigint references activities(id)
);