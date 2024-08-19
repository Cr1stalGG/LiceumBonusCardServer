create table accounts_activities(
    id bigserial primary key,
    account_id bigint references accounts(id),
    activity_id bigint references activities(id)
);