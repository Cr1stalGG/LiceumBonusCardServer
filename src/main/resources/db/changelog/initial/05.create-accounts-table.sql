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