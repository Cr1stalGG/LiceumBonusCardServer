create table response_statuses(
    id bigserial primary key,
    name varchar(60) not null unique,
    description text not null
)