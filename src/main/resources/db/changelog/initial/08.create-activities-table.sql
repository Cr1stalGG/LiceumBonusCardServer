create table activities(
    id bigserial primary key,
    name varchar(60) unique not null,
    description text,
    count_of_members int default 0 check(count_of_members > 0),
    activity_type_id bigint references activity_types(id)
);