create table activities(
    id uuid primary key,
    name varchar(60) not null,
    description text,
    count_of_members int default 0 check(count_of_members > 0),
    code varchar(16) unique not null,
    activity_type_id uuid references activity_types(id)
);