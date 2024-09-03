create table solved_activities(
    id uuid primary key,
    account_id uuid references accounts(id),
    activity_id uuid references activities(id),
    time_of_solving timestamp not null
);