create table roles(
    id bigserial primary key,
    name varchar(30) unique not null,
    description text
);

create table bonuses(
    id bigserial primary key,
    name varchar(60) unique not null,
    description text,
    price int default 1 check(price > 0),
    count int default 1 check(count > 0),
    time_of_end timestamp not null
);

create table cards(
    id bigserial primary key,
    number varchar(16) unique not null,
    balance int default 0 check(balance >= 0)
);

create table institutions(
    id bigserial primary key,
    name varchar unique not null,
    city varchar(60) unique not null
    --todo when use minio: logo_path varchar unique not null
    --todo add some info
);

create table accounts(
    id bigserial primary key,
    login varchar(40) not null unique,
    password varchar(64) not null,
    first_name varchar(30) not null,
    last_name varchar(30) not null,
    father_name varchar(30) not null,
    phone_number varchar(20) not null,
    card_id bigint unique references cards(id),
    institution_id bigint references institutions(id)
);

create table transaction_statuses(
    id bigserial primary key,
    name varchar(30) unique not null,
    description text not null
);

create table transactions(
    id bigserial primary key,
    balance int default 1 check(balance > 0),
    card_id bigint references cards(id),
    transaction_status_id bigint references transaction_statuses(id),
    message text not null,
    transaction_time timestamp not null
);

create table groups(
    id bigserial primary key,
    admin_id bigint references accounts(id),
    name varchar(60) unique not null,
    description text
);

create table activity_types(
    id bigserial primary key,
    name varchar(60) unique not null,
    cost int default 0 check(cost >= 0)
);

create table activities(
    id bigserial primary key,
    name varchar(60) unique not null,
    description text,
    count_of_members int default 0 check(count_of_members > 0),
    activity_type_id bigint references activity_types(id)
);

create table tickets(
    id bigserial primary key,
    code varchar(12) unique not null,
    account_id bigint references accounts(id),
    bonus_id bigint references bonuses(id)
);

create table response_statuses(
    id bigserial primary key,
    name varchar(60) not null unique,
    description text not null
)

create table responses(
    id bigserial primary key,
    response_status_id bigint references response_statuses(id),
    message text not null unique,
    account_id bigint references accounts(id),
    time_of_response timestamp not null
);

create table accounts_roles(
    id bigserial primary key,
    account_id bigint references accounts(id),
    role_id bigint references roles(id)
);

create table accounts_groups(
    id bigserial primary key,
    account_id bigint references accounts(id),
    group_id bigint references groups(id)
);

create table accounts_activities(
    id bigserial primary key,
    account_id bigint references accounts(id),
    activity_id bigint references activities(id)
);