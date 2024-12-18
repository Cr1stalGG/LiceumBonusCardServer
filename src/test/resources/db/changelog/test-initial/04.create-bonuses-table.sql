create table bonuses(
    id uuid primary key,
    name varchar(60) not null,
    description text,
    price int default 1 check(price > 0),
    count int default 1 check(count > 0),
    time_of_end timestamp not null,
    institution_id uuid references institutions(id)
);