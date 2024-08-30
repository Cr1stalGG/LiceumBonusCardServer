create table solved_activities(
    id bigserial primary key,
    account_id bigint references accounts(id),
    activity_id bigint references activities(id),
    time_of_solving timestamp not null
);