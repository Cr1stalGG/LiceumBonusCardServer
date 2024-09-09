create table responses(
    id uuid primary key,
    response_status_id uuid references response_statuses(id),
    message text not null,
    account_id uuid references accounts(id),
    time_of_response timestamp not null
);