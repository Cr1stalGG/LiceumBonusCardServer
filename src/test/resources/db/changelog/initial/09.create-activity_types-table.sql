create table activity_types(
    id uuid primary key,
    name varchar(60) not null,
    cost int default 0 check(cost >= 0),
    institution_id uuid references institutions(id)
);