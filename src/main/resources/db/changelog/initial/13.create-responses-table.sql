create table responses(
    id bigserial primary key,
    response_status_id bigint references response_statuses(id),
    message text not null unique,
    account_id bigint references accounts(id),
    time_of_response timestamp not null
);